package produitManagerForm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import produitManagerForm.metier.Produit;

public class ProduitDAO {
	private Connection connection; // connection a la BDD
	
	public static final String FIND_ALL_SQL =
			"SELECT id,nom,prix,poids,stock FROM produit";
	public static final String FIND_BY_ID_SQL =
			"SELECT id,nom,prix,poids,stock FROM produit WHERE id=?";
	public static final String INSERT_ONE_SQL =
			"INSERT INTO produit(nom,prix,poids,stock) VALUES (?,?,?,?)";
	public static final String UPDATE_ONE_SQL = 
			"UPDATE produit SET nom=?, prix=?, poids=?, stock=? WHERE id=?";
	public static final String DELETE_ONE_SQL =
			"DELETE FROM produit WHERE id=?";
	
	private PreparedStatement findAllStatement;
	private PreparedStatement findByIdStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement deleteOneStatement;
	
	
	
	// constructeur (appel� depuis BDDListener)
	public ProduitDAO(Connection connection) {
		this.connection = connection;
		try {
			findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
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
		List<Produit> produits = new ArrayList<>();
		
		try {
			// je nettoie le statement
			findAllStatement.clearParameters();
			// execution de la requette
			ResultSet rs = findAllStatement.executeQuery();
			// je percours les lignes renvoy�es par la base
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
			// je parcours la ligne renvoy�e par la base
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
			// c'est une mise � jour
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