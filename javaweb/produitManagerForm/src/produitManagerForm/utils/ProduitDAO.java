package produitManagerForm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import produitManagerForm.metier.Produit;

public class ProduitDAO {
	
	public final static int PAS_DE_TRI = 0;
	public final static int TRI_PAR_NOM = 1;
	public final static int TRI_PAR_PRIX = 2;
	public final static int TRI_PAR_POIDS = 3;
	
	private Connection connection; // connection a la BDD
	
	public static final String FIND_ALL_SQL =
			"SELECT id,nom,prix,poids,stock FROM produit";
	public static final String FIND_ALL_ORDER_NOM_SQL =
			"SELECT id,nom,prix,poids,stock FROM produit ORDER BY nom";
	public static final String FIND_ALL_ORDER_PRIX_SQL =
			"SELECT id,nom,prix,poids,stock FROM produit ORDER BY prix";
	public static final String FIND_ALL_ORDER_POIDS_SQL =
			"SELECT id,nom,prix,poids,stock FROM produit ORDER BY poids";
	
	public static final String FIND_BY_ID_SQL =
			"SELECT id,nom,prix,poids,stock FROM produit WHERE id=?";
	public static final String INSERT_ONE_SQL =
			"INSERT INTO produit(nom,prix,poids,stock) VALUES (?,?,?,?)";
	public static final String UPDATE_ONE_SQL = 
			"UPDATE produit SET nom=?, prix=?, poids=?, stock=? WHERE id=?";
	public static final String DELETE_ONE_SQL =
			"DELETE FROM produit WHERE id=?";
	
	private PreparedStatement findAllStatement;
	private PreparedStatement findAllOrderNomStatement;
	private PreparedStatement findAllOrderPrixStatement;
	private PreparedStatement findAllOrderPoidsStatement;
	private PreparedStatement findByIdStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement deleteOneStatement;
	
	
	
	// constructeur (appelé depuis BDDListener)
	public ProduitDAO(Connection connection) {
		this.connection = connection;
		try {
			findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
			findAllOrderNomStatement = connection.prepareStatement(FIND_ALL_ORDER_NOM_SQL);
			findAllOrderPrixStatement = connection.prepareStatement(FIND_ALL_ORDER_PRIX_SQL);
			findAllOrderPoidsStatement = connection.prepareStatement(FIND_ALL_ORDER_POIDS_SQL);
			findByIdStatement = connection.prepareStatement(FIND_BY_ID_SQL);
			updateOneStatement = connection.prepareStatement(UPDATE_ONE_SQL);
			insertOneStatement = connection.prepareStatement(INSERT_ONE_SQL);
			deleteOneStatement = connection.prepareStatement(DELETE_ONE_SQL);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Produit> findAll() {
		return findAll(PAS_DE_TRI);
	}
	public List<Produit> findAll(int choixTri) {
		List<Produit> produits = new ArrayList<>();
		// par defaut (si pas de tri choisi), on ne tri pas
		PreparedStatement findStatement= findAllStatement;
		// sinon, on choisi une des requettes avec order by en fonction du tri choisi
		switch(choixTri) {
			case TRI_PAR_NOM:
				findStatement = findAllOrderNomStatement;
				break;
			case TRI_PAR_PRIX:
				findStatement = findAllOrderPrixStatement;
				break;
			case TRI_PAR_POIDS:
				findStatement = findAllOrderPoidsStatement;
				break;
		}
		
		try {
			// je nettoie le statement
			findStatement.clearParameters();
			// execution de la requette
			ResultSet rs = findStatement.executeQuery();
			// je percours les lignes renvoyées par la base
			while (rs.next()) {
				// pour chaque ligne, j'instancie l'objet produit correspondant
				produits.add(new Produit(rs.getInt("id"),
										rs.getString("nom"),
										rs.getDouble("prix"),
										rs.getDouble("poids"),
										rs.getInt("stock")));
			}
			// fermeture du resultSet
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return produits;
	}
	
	public Produit findByID(int id) {
		Produit p = null;
		try {
			// je nettoie le statement
			findByIdStatement.clearParameters();
			// j'attache le parametre id a la requette
			findByIdStatement.setInt(1, id);
			
			// execution de la requette
			ResultSet rs = findByIdStatement.executeQuery();
			// je parcours la ligne renvoyée par la base
			if (rs.next()) {
				p = new Produit(rs.getInt("id"),
										rs.getString("nom"),
										rs.getDouble("prix"),
										rs.getDouble("poids"),
										rs.getInt("stock"));
			}
			// fermeture du resultSet
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return p;
	}
	
	public void save(Produit p) {
		if (p.getId() == 0) {
			// c'est une insertion
			try {
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, p.getNom());
				insertOneStatement.setDouble(2, p.getPrix());
				insertOneStatement.setDouble(3, p.getPoids());
				insertOneStatement.setInt(4, p.getStock());
				// execution de l'insertion
				insertOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
			
		}
		else {
			// c'est une mise à jour
			try {
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, p.getNom());
				updateOneStatement.setDouble(2, p.getPrix());
				updateOneStatement.setDouble(3, p.getPoids());
				updateOneStatement.setInt(4, p.getStock());
				updateOneStatement.setInt(5, p.getId());
				// execution de la requette
				updateOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public void deleteOne(int id) {
		try {
			deleteOneStatement.clearParameters();
			deleteOneStatement.setInt(1, id);
			deleteOneStatement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	
	
}
