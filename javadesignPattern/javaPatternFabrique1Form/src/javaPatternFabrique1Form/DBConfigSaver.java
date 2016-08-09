package javaPatternFabrique1Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DBConfigSaver implements IConfigApp {
	// stockage des valeur a sauvegarder en base (comme avant)
	private Map<String,String> values;
	
	
	private Connection connection;
	// les noms des colonnes que nous devrons utiliser dans la table de la base de donnée
	// nous les gardons, car nous en avaons besoin a 2 moment
	//  1 -> a la création des requettes sql
	//  2 -> pour récupérer les valeur depuis le resultset lors d'une interrogation de la base
	private String cleColumnName;
	private String valueColumnName;
	
	private PreparedStatement findAll;
	private PreparedStatement clearTable;
	private PreparedStatement insertOne;
	
	// dans ce constructeur avec BEAUCOUP de parametres (d'ou l'utilisation d'un builder)
	// le driver demandé est chargé, puis la connection est ouverte
	// vers l'url fournie, avec le login choisi et le password fournit également
	// pour construire les requettes SQL, nous avons aussi besoin du nom de la table
	// et du nom de ses 2 colonnes
	public DBConfigSaver(String driverClass,
						String url,
						String login,
						String password,
						String tableName,
						String cleColumnName,
						String valueColumnName) {
		values = new HashMap<>();
		// nous sauvegardons les noms de colonnes pour plus tard (dans le load())
		this.cleColumnName = cleColumnName;
		this.valueColumnName = valueColumnName;
		try {
			// chargement du driver choisi
			Class.forName(driverClass);
			// connection avec les parametres choisis
			connection = DriverManager.getConnection(url, login, password);
			// par exemple, si on a
			//	cleColumnName = "appCle"
			//  valueColumnName = "appValue"
			//  tableName = "appTable"
			// -> "select appCle,appValue from appTable"
			findAll = connection.prepareStatement("select " + cleColumnName
												+ ", " + valueColumnName
												+ " from " + tableName);
			insertOne = connection.prepareStatement("insert into " + tableName
												+ "(" + cleColumnName 
												+ ", " + valueColumnName
												+ ") VALUES (?,?)");
			clearTable = connection.prepareStatement("delete from " + tableName);
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
				insertOne.clearParameters();
				insertOne.setString(1, kv.getKey());
				insertOne.setString(2, kv.getValue());
				insertOne.executeUpdate();
			}
		} catch (SQLException e) {e.printStackTrace();}

	}

	@Override
	public void load() {
		values.clear();
		try {
			ResultSet rs = findAll.executeQuery();
			while (rs.next()) {
				values.put(rs.getString(cleColumnName), rs.getString(valueColumnName));
				//setConfigValue(rs.getString("cle"), rs.getString("valeur"));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();	}

	}

	public static class Builder {
		
		private String driverClass;
		private String url;
		private String login;
		private String password;
		private String tableName;
		private String cleColumnName;
		private String valueColumnName;
		// cet objet sert a construire "simplement" me DBConfigSaver
		// comme l'url est obligatoire, nous la demandons a la construction
		// du builder
		public Builder(String url) {
			this.url = url;
			// a part l'url qui doit être fournie, tous les autres parametres
			// nécéssaire a la construction d'un DBConfigSaver on des valeurs par
			// défaut, comme par exemple le driver mysql si on en demande pas un autre
			driverClass = "com.mysql.jdbc.Driver";
			login = "root";
			password = "";
			tableName = "tableconf";
			cleColumnName = "confname";
			valueColumnName = "confvalue";
		}
		
		public Builder driverClass(String driverClass) {
			this.driverClass = driverClass;
			return this;
		}
		public Builder login(String login) {
			this.login = login;
			return this;
		}
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		public Builder tableName(String tableName) {
			this.tableName = tableName;
			return this;
		}
		public Builder cleColumnName(String cleColumnName) {
			this.cleColumnName = cleColumnName;
			return this;
		}
		public Builder valueColumnName(String valueColumnName) {
			this.valueColumnName = valueColumnName;
			return this;
		}
		
		public DBConfigSaver build() {
			// ici, nous instancions finalement le DBConfigSaver
			// en lui transmettant dans le bon ordre tous ses parametres
			// de configurations
			return new DBConfigSaver(driverClass,
									url,
									login,
									password,
									tableName,
									cleColumnName,
									valueColumnName);
		}
		
	}
	
}
