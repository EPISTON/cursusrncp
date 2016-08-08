package javaPatternFabrique1Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DatabaseConfigApp implements IConfigApp {

	public static final String SELECT_VALUES = "select cle,valeur from configvalue";
	public static final String SELECT_VALUE_BY_CLE = "select cle,valeur from configvalue where cle=?";
	public static final String INSERT_VALUE = "insert into configvalue(cle,valeur) VALUES (?,?)";
	public static final String DELETE_ALL = "delete from configvalue";
	
	
	private PreparedStatement findAll;
	private PreparedStatement findByCle;
	private PreparedStatement insertValue;
	private PreparedStatement clearTable;
	
	
	private Map<String,String> values;
	private Connection connection;
	
	// classe du driver
	// url de connection
	// login
	// password
	// séparé par des ;
	public DatabaseConfigApp(String connectionParameters) {
		values = new HashMap<String, String>();
		String[] parameters = connectionParameters.split(";");
		try {
			Class.forName(parameters[0]);
			this.connection = DriverManager.getConnection(parameters[1],
														  parameters[2],
														  (parameters.length <4)? "" : parameters[3]);
			findAll = connection.prepareStatement(SELECT_VALUES);
			findByCle = connection.prepareStatement(SELECT_VALUE_BY_CLE);
			insertValue = connection.prepareStatement(INSERT_VALUE);
			clearTable = connection.prepareStatement(DELETE_ALL);
		} catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
	
	
	@Override
	public String getConfigValue(String name) {
		return values.get(name);
	}

	@Override
	public void setConfigValue(String name, String value) {
		values.put(name, value);
	}

	@Override
	public void save() {
		try {
			// nous vidons la table
			clearTable.executeUpdate();
			// on peu parcourir une Map de trois maniere
			// via les cle -> keyset()
			// directement via les valeur -> values()
			// ou enfin, via la collection des cle/valeur, sous forme d'Entry
			// entrySet()
			for (Entry<String , String> kv : values.entrySet()) {
				insertValue.clearParameters();
				insertValue.setString(1, kv.getKey());
				insertValue.setString(2, kv.getValue());
				insertValue.executeUpdate();
			}
		} catch (SQLException e) {e.printStackTrace();}
	}

	@Override
	public void load() {
		values.clear();
		try {
			ResultSet rs = findAll.executeQuery();
			while (rs.next()) {
				values.put(rs.getString("cle"), rs.getString("valeur"));
				//setConfigValue(rs.getString("cle"), rs.getString("valeur"));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();	}
	}

}
