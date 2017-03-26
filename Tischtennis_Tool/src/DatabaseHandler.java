import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class DatabaseHandler {

	private static Connection c;
	private static java.sql.Statement statement;
	private static PreparedStatement personDeleteByIDStatement;

	public DatabaseHandler() {

	}

	public static boolean tryToConnect(String password) {
		boolean success;
		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/minis", "root", password);
			statement = c.createStatement();
			personDeleteByIDStatement = (PreparedStatement) c.prepareStatement("delete from teilnehmer where id = ?");
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	public static boolean exists(String vorname, String nachname,
			String geburtstag) throws SQLException {
		ResultSet result = statement
				.executeQuery("select * from teilnehmer where (teilnehmer.vorname = '"
						+ vorname
						+ "' and teilnehmer.nachname = '"
						+ nachname
						+ "' "
						+ "and teilnehmer.geburtstag = '"
						+ geburtstag
						+ "');");
		return result.next();
	}

	public static void insert(String vorname, String nachname,
			String ortsentscheid, String adresse, String geburtstag,
			String geschlecht) throws SQLException {
		String insertQuery = "insert into teilnehmer"
				+ " (id, vorname, nachname, ortsentscheid, adresse, geburtstag, geschlecht)"
				+ " values ('" + getID() + "', '" + vorname + "', '" + nachname
				+ "', '" + ortsentscheid + "', '" + adresse + "', '"
				+ geburtstag + "', '" + geschlecht + "')";
		statement.execute(insertQuery);
	}

	public static int[] getAllNumbers() throws SQLException {
		int[] returnValues = new int[6];
		for (int i = 0; i < returnValues.length; i++) {
			returnValues[i] = 0;
		}

		ResultSet queryResult = statement
				.executeQuery("select * from teilnehmer");
		while (queryResult.next()) {
			int age = CentralPanel.getAgeCategory(Integer.valueOf(queryResult
					.getString("geburtstag").substring(0, 4)));
			String gender = queryResult.getString("geschlecht").substring(0, 1);
			if (age == 8) {
				if (gender.equalsIgnoreCase("w")) {
					returnValues[0]++;
				} else {
					returnValues[1]++;
				}
			} else if (age == 10) {
				if (gender.equalsIgnoreCase("w")) {
					returnValues[2]++;
				} else {
					returnValues[3]++;
				}
			} else if (gender.equalsIgnoreCase("w")) {
				returnValues[4]++;
			} else {
				returnValues[5]++;
			}
		}

		return returnValues;
	}

	public static void update() {

	}

	private static int getID() throws SQLException {
		ResultSet result = statement
				.executeQuery("select max(id) as maxid from teilnehmer");
		int max = -1;
		while (result.next()) {
			max = result.getInt("maxid");
		}
		return max + 1;
	}

	public static List<Person> getAllParticipants() throws SQLException {
		List<Person> list = new ArrayList<Person>();
		ResultSet result = statement.executeQuery("select * from teilnehmer");
		while (result.next()) {
			list.add(new Person(result.getInt("id"), result
					.getString("vorname"), result.getString("nachname"), result
					.getString("ortsentscheid"), result.getString("adresse"),
					result.getString("geburtstag"), result
							.getString("geschlecht")));
		}

		return list;
	}

	public static void removeFromDatabase(int id) throws SQLException {
		personDeleteByIDStatement.setInt(1, id);
		personDeleteByIDStatement.executeUpdate();
	}
}
