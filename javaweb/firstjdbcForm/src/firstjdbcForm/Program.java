package firstjdbcForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

	public static void main(String[] args) {
		try {
			// chargement du driver mysql
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/basetest1",
					"root",
					"");
			System.out.println("connection réussie!");
			// creation d'un objet de requetage
			Statement request = connection.createStatement();
			// execution d'une requette simple
			ResultSet rs = request.executeQuery("select id,titre from post");
			// le resultSet permet de parcourir les données renvoyées
			while (rs.next()) {
				System.out.println("post d'id " + rs.getInt("id")
								+ " et de titre '" + rs.getString("titre") + "'");
			}
			rs.close();
			connection.close();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
