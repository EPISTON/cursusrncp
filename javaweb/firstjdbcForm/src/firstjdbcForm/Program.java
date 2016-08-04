package firstjdbcForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import javax.swing.text.SimpleAttributeSet;

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
			System.out.println("-------------------------------");
			// requete préparée ou paramétrée
			PreparedStatement request2 = connection.prepareStatement(
					"select id, titre from post where id=?");
			// j'attache un parametre a la requette, qui remplacera le ? no 1
			request2.setInt(1, 2); // je veux le post d'id = 2
			rs = request2.executeQuery();
			while (rs.next()) {
				System.out.println("post d'id " + rs.getInt("id")
								+ " et de titre '" + rs.getString("titre") + "'");
			}
			rs.close();
			System.out.println("-------------------------------");
			// je vide les valeurs des anciens parametres
			request2.clearParameters();
			// maintenant, nouvelle valeur pour le where 
			request2.setInt(1, 4);
			rs = request2.executeQuery();
			while (rs.next()) {
				System.out.println("post d'id " + rs.getInt("id")
								+ " et de titre '" + rs.getString("titre") + "'");
			}
			rs.close();
			
			PreparedStatement request3 = connection.prepareStatement(
					"select count(id) from post where titre like ?");
			request3.setString(1, "%malade%");
			rs = request3.executeQuery();
			if (rs.next()) {
				// je récupere la premiere colonne du resultat
				int nblignes = rs.getInt(1);
				System.out.println("nombre de post = " + nblignes);
			}
			rs.close();
			
			Scanner lecteur = new Scanner(System.in);
			System.out.println("titre du mouveau post ?");
			String saisietitre = lecteur.nextLine();
			System.out.println("corps du nouveau post ?");
			String saisiecorps = lecteur.nextLine();
			
			PreparedStatement stat4 = connection.prepareStatement(
					"insert into post(titre,corps,dateCreation) VALUES(?,?,?)");
			stat4.setString(1, saisietitre);
			stat4.setString(2, saisiecorps);
			// je creer un objet java.util.date sur la date courante
			// je le converti en java.sql.date, et je la passe a setDate
			stat4.setDate(3, new java.sql.Date(new Date().getTime()));
			int nblignemodifies = stat4.executeUpdate();
			System.out.println(nblignemodifies + " lignes on été insérés");
			
			System.out.println("no du poste a modifier ?");
			int pid = Integer.parseInt(lecteur.nextLine());
			System.out.println("nouveau titre du post ?");
			saisietitre = lecteur.nextLine();
			PreparedStatement stat5 = connection.prepareStatement(
					"update post set titre=? where id=?");
			stat5.setString(1, saisietitre);
			stat5.setInt(2, pid);
			nblignemodifies = stat5.executeUpdate();
			System.out.println(nblignemodifies + " lignes on été modifiées");
			
			PreparedStatement stat6 = connection.prepareStatement("delete from post where id=?");
			System.out.println("no du poste a supprimer ?");
			pid = Integer.parseInt(lecteur.nextLine());
			stat6.setInt(1, pid);
			nblignemodifies = stat6.executeUpdate();
			System.out.println(nblignemodifies + " lignes on été suprrimées");
			
			
			
			
			
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
