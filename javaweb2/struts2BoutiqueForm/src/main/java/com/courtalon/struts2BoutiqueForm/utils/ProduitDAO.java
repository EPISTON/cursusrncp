package com.courtalon.struts2BoutiqueForm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.struts2BoutiqueForm.metier.Categorie;
import com.courtalon.struts2BoutiqueForm.metier.Produit;



public class ProduitDAO {

	public static final String FIND_ALL_SQL 
		= "select p.id, p.nom, p.prix, p.poids, p.stock, p.categorieId, c.libelle from produit as p inner join"
				+ " categorie as c on p.categorieId = c.id";
	public static final String FIND_BY_ID_SQL
		= "select id, nom, prix, poids, stock, categorieId from produit where id=?";
	public static final String INSERT_ONE_SQL
		= "insert into produit (nom, prix, poids, stock, categorieId) values(?,?,?,?,?)";
	public static final String UPDATE_ONE_SQL
		= "update produit  set nom=?, prix=?, poids=?, stock=?,categorieId=?  where id=?";
	public static final String DELETE_ONE_SQL
		= "delete from produit where id=?";
	public static final String LIST_CATEGORIES_SQL 
		= "select id, libelle from categorie";
			
	
	
	private Logger log = LogManager.getLogger(ProduitDAO.class);
	
	private Connection connection;

	private PreparedStatement findAllStatement;
	private PreparedStatement findByIDStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement deleteOneStatement;
	private PreparedStatement listCategoriesStatement;
	
	public ProduitDAO(Connection connection) {
		this.connection = connection;
		
		try {
			findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
			findByIDStatement = connection.prepareStatement(FIND_BY_ID_SQL);
			insertOneStatement = connection.prepareStatement(INSERT_ONE_SQL);
			updateOneStatement = connection.prepareStatement(UPDATE_ONE_SQL);
			deleteOneStatement = connection.prepareStatement(DELETE_ONE_SQL);
			listCategoriesStatement = connection.prepareStatement(LIST_CATEGORIES_SQL);
		} catch (SQLException e) {log.error(e);}
	}
	
	public List<Produit> findAll() {
		log.info("appel de findAll de produitDAO");
		List<Produit> produits = new ArrayList<>();
		try {
			// effacer les parametres précédent s'il y en avait
			findAllStatement.clearParameters();
			// executer la requette
			ResultSet rs = findAllStatement.executeQuery();
			// parcourir les resultat de l'execution
			while (rs.next()) {
				// pour chaque ligne, ajouter un objet message avec les données correspondantes
				// en provenance de la ligne
				
				// je récupère les données provenant de la table produit
				// et j'instancie l'objet Produit correspondant
				Produit p = new Produit(rs.getInt("id"),
						 rs.getString("nom"),
						 rs.getDouble("prix"),
						 rs.getDouble("Poids"),
						 rs.getInt("stock"),
						 rs.getInt("categorieId"));
				// je récupere les colonnes provenant de Categorie associéee a ce Produit
				// et j'instancie un Objet categorie, que
				// j'ajout a l'objet Produit
				p.setCategorie(new Categorie(rs.getInt("categorieId"),
								rs.getString("libelle")));
				produits.add(p);
			}
			// fermer le resultSet
			rs.close();
		} catch (SQLException e) {log.error(e);}
		return produits;
	}
	
	public Produit findById(int id) {
		log.info("appel de findById de produitDAO");
		Produit produit = null;
		try {
			// effacer les parametres précédent s'il y en avait
			findByIDStatement.clearParameters();
			findByIDStatement.setInt(1, id);
			// executer la requette
			ResultSet rs = findByIDStatement.executeQuery();
			// parcourir les resultat de l'execution
			if (rs.next()) {
				produit = new Produit(rs.getInt("id"),
						 rs.getString("nom"),
						 rs.getDouble("prix"),
						 rs.getDouble("Poids"),
						 rs.getInt("stock"),
						 rs.getInt("categorieId"));
			}
			// fermer le resultSet
			rs.close();
		} catch (SQLException e) {log.error(e);}
		return produit;
	}
	
	public void save(Produit produit) {
		if (produit.getId() > 0) {
			try {
				// mise a jour d'une enchere existante
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, produit.getNom());
				updateOneStatement.setDouble(2, produit.getPrix());
				updateOneStatement.setDouble(3, produit.getPoids());
				updateOneStatement.setInt(4, produit.getStock());
				updateOneStatement.setInt(5, produit.getCategorieId());
				updateOneStatement.setInt(6, produit.getId());
				updateOneStatement.executeUpdate();
			} catch (SQLException e) {
				log.error(e);
			}
		}
		else {
			try {
				// creation d'une enchere 
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, produit.getNom());
				insertOneStatement.setDouble(2, produit.getPrix());
				insertOneStatement.setDouble(3, produit.getPoids());
				insertOneStatement.setInt(4, produit.getStock());
				insertOneStatement.setInt(5, produit.getCategorieId());
				insertOneStatement.executeUpdate();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
	
	public void deleteOne(int id) {
		try {
			deleteOneStatement.clearParameters();
			deleteOneStatement.setInt(1, id);
			deleteOneStatement.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
	public List<Categorie> listCategorie() {
		log.info("appel de findAll de produitDAO");
		List<Categorie> cats = new ArrayList<>();
		try {
			listCategoriesStatement.clearParameters();
			ResultSet rs = listCategoriesStatement.executeQuery();
			while (rs.next()) {
				 cats.add(new Categorie(rs.getInt("id"),
						 	rs.getString("libelle")));
			}
			rs.close();
		} catch (SQLException e) {log.error(e);}
		return cats;
	}
}
