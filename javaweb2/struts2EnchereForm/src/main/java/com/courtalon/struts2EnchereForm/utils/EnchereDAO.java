package com.courtalon.struts2EnchereForm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.struts2EnchereForm.metier.Enchere;



public class EnchereDAO {
	
	public static final String FIND_ALL_SQL 
		= "select id, description, prixDepart, enchereMinimum, prixActuel from enchere";
	public static final String FIND_BY_ID_SQL
		= "select id, description, prixDepart, enchereMinimum, prixActuel from enchere where id=?";
	public static final String INSERT_ONE_SQL
		= "insert into enchere (description, prixDepart, enchereMinimum, prixActuel) values(?,?,?,?)";
	public static final String UPDATE_ONE_SQL
		= "update enchere  set description=?, prixDepart=?, enchereMinimum=?, prixActuel=? where id=?";
	public static final String DELETE_ONE_SQL
		= "delete from enchere where id=?";
	
	
	
	private Logger log = LogManager.getLogger(EnchereDAO.class);
	
	private Connection connection;

	private PreparedStatement findAllStatement;
	private PreparedStatement findByIDStatement;
	private PreparedStatement insertOneStatement;
	private PreparedStatement updateOneStatement;
	private PreparedStatement deleteOneStatement;
	
	public EnchereDAO(Connection connection) {
		this.connection = connection;
		
		try {
			findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
			findByIDStatement = connection.prepareStatement(FIND_BY_ID_SQL);
			insertOneStatement = connection.prepareStatement(INSERT_ONE_SQL);
			updateOneStatement = connection.prepareStatement(UPDATE_ONE_SQL);
			deleteOneStatement = connection.prepareStatement(DELETE_ONE_SQL);
		} catch (SQLException e) {log.error(e);}
	}
	
	public List<Enchere> findAll() {
		log.info("appel de findAll de enchereDAO");
		List<Enchere> encheres = new ArrayList<>();
		try {
			// effacer les parametres précédent s'il y en avait
			findAllStatement.clearParameters();
			// executer la requette
			ResultSet rs = findAllStatement.executeQuery();
			// parcourir les resultat de l'execution
			while (rs.next()) {
				// pour chaque ligne, ajouter un objet message avec les données correspondantes
				// en provenance de la ligne
				encheres.add(new Enchere(rs.getInt("id"),
										 rs.getString("description"),
										 rs.getDouble("prixDepart"),
										 rs.getDouble("enchereMinimum"),
										 rs.getDouble("prixActuel")));
			}
			// fermer le resultSet
			rs.close();
		} catch (SQLException e) {log.error(e);}
		return encheres;
	}
	
	public Enchere findById(int id) {
		log.info("appel de findById de enchereDAO");
		Enchere enchere = null;
		try {
			// effacer les parametres précédent s'il y en avait
			findByIDStatement.clearParameters();
			findByIDStatement.setInt(1, id);
			// executer la requette
			ResultSet rs = findByIDStatement.executeQuery();
			// parcourir les resultat de l'execution
			if (rs.next()) {
				enchere = new Enchere(rs.getInt("id"),
										 rs.getString("description"),
										 rs.getDouble("prixDepart"),
										 rs.getDouble("enchereMinimum"),
										 rs.getDouble("prixActuel"));
			}
			// fermer le resultSet
			rs.close();
		} catch (SQLException e) {log.error(e);}
		return enchere;
	}
	
	public void save(Enchere enchere) {
		if (enchere.getId() > 0) {
			try {
				// mise a jour d'une enchere existante
				updateOneStatement.clearParameters();
				updateOneStatement.setString(1, enchere.getDescription());
				updateOneStatement.setDouble(2, enchere.getPrixDepart());
				updateOneStatement.setDouble(3, enchere.getEnchereMinimum());
				updateOneStatement.setDouble(4, enchere.getPrixActuel());
				updateOneStatement.setInt(5, enchere.getId());
				updateOneStatement.executeUpdate();
			} catch (SQLException e) {
				log.error(e);
			}
		}
		else {
			try {
				// creation d'une enchere 
				insertOneStatement.clearParameters();
				insertOneStatement.setString(1, enchere.getDescription());
				insertOneStatement.setDouble(2, enchere.getPrixDepart());
				insertOneStatement.setDouble(3, enchere.getEnchereMinimum());
				insertOneStatement.setDouble(4, enchere.getPrixActuel());
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
}
