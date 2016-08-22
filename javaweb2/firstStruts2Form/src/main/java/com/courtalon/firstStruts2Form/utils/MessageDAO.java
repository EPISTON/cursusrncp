package com.courtalon.firstStruts2Form.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.firstStruts2Form.metier.Message;

public class MessageDAO {
	public static final String FIND_ALL_SQL = "select id, titre, corps from message";
	
	private Logger log = LogManager.getLogger(MessageDAO.class);
	
	private Connection connection;

	private PreparedStatement findAllStatement;
	
	public MessageDAO(Connection connection) {
		this.connection = connection;
		
		try {
			findAllStatement = connection.prepareStatement(FIND_ALL_SQL);
		} catch (SQLException e) {log.error(e);}
	}
	
	public List<Message> findAll() {
		List<Message> messages = new ArrayList<>();
		try {
			// effacer les parametres précédent s'il y en avait
			findAllStatement.clearParameters();
			// executer la requette
			ResultSet rs = findAllStatement.executeQuery();
			// parcourir les resultat de l'execution
			while (rs.next()) {
				// pour chaque ligne, ajouter un objet message avec les données correspondantes
				// en provenance de la ligne
				messages.add(new Message(rs.getInt("id"),
										 rs.getString("titre"),
										 rs.getString("corps")));
			}
			// fermer le resultSet
			rs.close();
		} catch (SQLException e) {log.error(e);}
		return messages;
	}
	
	
}
