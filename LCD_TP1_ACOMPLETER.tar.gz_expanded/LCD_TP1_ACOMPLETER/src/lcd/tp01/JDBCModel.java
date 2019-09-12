package lcd.tp01;

import java.sql.*;
import java.io.*;
import java.util.Map;
import java.util.Vector;
import java.util.Collection;

class JDBCModel implements IModel {

	private Connection connection = null;
	private static final String[] tableNames = { "MOVIE_djam", "PEOPLE_djam", "DIRECTOR_djam", "ROLE_djam" };

	JDBCModel(String username, String password, String base) throws SQLException, ClassNotFoundException {

		/*
		 * À COMPLETER CONNEXION À LA BASE POSTGRESQL SUR LA MACHINE tp-postgres
		 */

	}

	public String[] getTableNames() {
		return tableNames;
	}

	// Ferme explicitement la connexion.
	public void close() throws Exception {
		if (connection != null) {
			/*
			 * À COMPLETER FERMER LA CONNEXION ET INITIALISER À NULL
			 */
		}

	}

	// Appelé lors de la destruction de l'objet par le GC.
	protected void finalize() throws Throwable {
		this.close();
	}

	public void initialize() throws SQLException {
		if (connection != null) {
			/*
			 * À COMPLÉTER - DÉTRUIRE LES TABLES EXISTANTES - CRÉER LES TABLES
			 * AVEC LE SCHEMA DEMANDÉ
			 */
			  Statement stat = connection.createStatement();
			  stat.executeUpdate("DROP TABLE IF EXISTS MOVIE_djam CASCADE ");
			  stat.executeUpdate("DROP TABLE IF EXISTS PEOPLE_djam CASCADE ");
			  stat.executeUpdate("DROP TABLE IF EXISTS DIRECTOR_djam CASCADE ");
			  stat.executeUpdate("DROP TABLE IF EXISTS ROLE_djam CASCADE ");
			  stat.executeUpdate (" CREATE TABLE PEOPLE_djam (pid INTEGER, "
			  		+ "firstname VARCHAR(30),"
                    + "lastname VARCHAR(30),"
                    +  "PRIMARY KEY(pid)");

			  
		}

	}

	private void fillMovie(BufferedReader r) throws SQLException, IOException {

		/*
		 * À COMPLÉTER : lire 'r' ligne à ligne et remplir la table MOVIE. On
		 * pourra utiliser String.split() pour séparer selon des ';'.
		 */
		  Statement stat = connection.createStatement();
		  String line = null; 
		  while ((line = r.readLine()) != null ) {
			  String[] fields = line.split(";");
			  String insert = "INSERT INTO MOVIE_djam VALUES ( "
			  		+ fields[0] + ";"
	                + fields[1] + 	","
	                + fields[2] + 	","
	                + fields[3] + 	","
	                + fields[4] + 	")";
			  				
			  
		  }
		  

	}

	private void fillPeople(BufferedReader r) throws SQLException, IOException {
		/*
		 * À COMPLÉTER : lire 'r' ligne à ligne et remplir la table MOVIE. On
		 * pourra utiliser String.split() pour séparer selon des ';'.
		 */
		  String line; 
		  String insert = "INSERT INTO PEOPLE_djam VALUES(?,??)";
		  PreparedStatement  stat = connection.prepareStatement(insert);
		  while ((line = r.readLine()) != null ) {
			  String[] fields = line.split(";");
			  stat.setInt(1, Integer.parseInt(fields[0]));
			  stat.SetString(2, fields[1]);
			  stat.
		  
	}

	private void fillDirector(BufferedReader r) throws SQLException, IOException {
		/*
		 * À COMPLÉTER : lire 'r' ligne à ligne et remplir la table MOVIE. On
		 * pourra utiliser String.split() pour séparer selon des ';'.
		 */

	}

	private void fillRole(BufferedReader r) throws SQLException, IOException {
		/*
		 * À COMPLÉTER : lire 'r' ligne à ligne et remplir la table MOVIE. On
		 * pourra utiliser String.split() pour séparer selon des ';'.
		 */
	}

	public void fillTables(Map<String, File> files) throws Exception {
		if (connection != null) {
			try {
				/*
				 * CADEAU, BIEN LIRE LE CODE MAIS RIEN À COMPLÉTER
				 */
				connection.setAutoCommit(false);
				File f;
				f = files.get("MOVIE_djam");
				if (f == null)
					throw new Exception();
				fillMovie(new BufferedReader(new FileReader(f)));

				f = files.get("PEOPLE_djam");
				if (f == null)
					throw new Exception();
				fillPeople(new BufferedReader(new FileReader(f)));

				f = files.get("DIRECTOR_djam");
				if (f == null)
					throw new Exception();
				fillDirector(new BufferedReader(new FileReader(f)));

				f = files.get("ROLE_djam");
				if (f == null)
					throw new Exception();
				fillRole(new BufferedReader(new FileReader(f)));

				connection.commit();

			} catch (Exception e) {
				connection.rollback();
				close();
				throw (e);
			}

		}
	}

	public Collection<String> query(String pattern) throws Exception {

		if (connection != null) {
			pattern = "'%" + pattern + "%'";
			Vector<String> v = new Vector<String>();

			/*
			 * À COMPLÉTER. ÉCRIRE DES REQUÊTES POUR REMPLIR v
			 * AFIN QU'IL CONTIENNE DES CHAINES DE LA FORME
			   Titre, Année, durée, Real1, …, Realn, Acteur 1 : Role 1, … Acteur m: Role m
			 */
			   Statement stat = connection.createStatement();
			   ResultSet res = stat.executeQuery("SELECT title AS t FROM MOVIE_djam WHERE title LIKE "+pattern); 
			   
			   while (res.next()) {
				     v.add(res.getString("t"));
				     
			   }

			return v;

		} else
			throw new Exception();

	}
}
