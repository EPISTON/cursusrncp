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
	
	public static final String FIND_ALL_SQL = "select id, description, prixDepart, enchereMinimum, prixActuel from enchere";
	
	private Logger log = LogManager.getLogger(EnchereDAO.class);
	
	private Connection connection;

	private PreparedStatement findAllStatement;
	
	public EnchereDAO(Connection connection) {
		this.connection = connection;
		
		try {
			findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
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
	

}
