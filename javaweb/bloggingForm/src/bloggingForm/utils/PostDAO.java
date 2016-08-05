package bloggingForm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bloggingForm.metier.Post;



public class PostDAO {
	
	public final static int PAS_DE_TRI = 0;
	public final static int TRI_PAR_TITRE = 1;
	
	private Connection connection; // connection a la BDD
	
	public static final String FIND_ALL_SQL =
			"SELECT id,titre,corps,categorie,auteur FROM post";
	public static final String FIND_ALL_ORDER_TITRE_SQL =
			"SELECT id,titre,corps,categorie,auteur FROM post ORDER BY titre";
	
	public static final String FIND_BY_ID_SQL =
			"SELECT id,titre,corps,categorie,auteur FROM post WHERE id=?";
	public static final String INSERT_ONE_SQL =
			"INSERT INTO post(titre,corps,categorie,auteur) VALUES (?,?,?,?)";
	public static final String UPDATE_ONE_SQL = 
			"UPDATE post SET titre=?, corps=?, categorie=?, auteur=? WHERE id=?";
	public static final String DELETE_ONE_SQL =
			"DELETE FROM post WHERE id=?";
	
	private PreparedStatement findAllStatement;
	private PreparedStatement findAllOrderTitreStatement;
	private PreparedStatement findByIdStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement deleteOneStatement;
	
	
	
	// constructeur (appelé depuis BDDListener)
	public PostDAO(Connection connection) {
		this.connection = connection;
		try {
			findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
			findAllOrderTitreStatement = connection.prepareStatement(FIND_ALL_ORDER_TITRE_SQL);
			findByIdStatement = connection.prepareStatement(FIND_BY_ID_SQL);
			updateOneStatement = connection.prepareStatement(UPDATE_ONE_SQL);
			insertOneStatement = connection.prepareStatement(INSERT_ONE_SQL);
			deleteOneStatement = connection.prepareStatement(DELETE_ONE_SQL);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Post> findAll() {
		return findAll(PAS_DE_TRI);
	}
	public List<Post> findAll(int choixTri) {
		List<Post> posts = new ArrayList<>();
		// par defaut (si pas de tri choisi), on ne tri pas
		PreparedStatement findStatement= findAllStatement;
		// sinon, on choisi une des requettes avec order by en fonction du tri choisi
		switch(choixTri) {
			case TRI_PAR_TITRE:
				findStatement = findAllOrderTitreStatement;
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
				posts.add(new Post(rs.getInt("id"),
										rs.getString("titre"),
										rs.getString("corps"),
										rs.getString("categorie"),
										rs.getString("auteur")));
			}
			// fermeture du resultSet
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return posts;
	}
	
	public Post findByID(int id) {
		Post p = null;
		try {
			// je nettoie le statement
			findByIdStatement.clearParameters();
			// j'attache le parametre id a la requette
			findByIdStatement.setInt(1, id);
			
			// execution de la requette
			ResultSet rs = findByIdStatement.executeQuery();
			// je parcours la ligne renvoyée par la base
			if (rs.next()) {
				p = new Post(rs.getInt("id"),
						rs.getString("titre"),
						rs.getString("corps"),
						rs.getString("categorie"),
						rs.getString("auteur"));
			}
			// fermeture du resultSet
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return p;
	}
	
	public void save(Post p) {
		if (p.getId() == 0) {
			// c'est une insertion
			try {
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, p.getTitre());
				insertOneStatement.setString(2, p.getCorps());
				insertOneStatement.setString(3, p.getCategorie());
				insertOneStatement.setString(4, p.getAuteur());
				// execution de l'insertion
				insertOneStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
			
		}
		else {
			// c'est une mise à jour
			try {
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, p.getTitre());
				updateOneStatement.setString(2, p.getCorps());
				updateOneStatement.setString(3, p.getCategorie());
				updateOneStatement.setString(4, p.getAuteur());
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
