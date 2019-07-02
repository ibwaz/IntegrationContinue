package stationMetro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import DB_PARAM.ConnectionBD;

public class station {
	private int id_station;
	private String Nom;
	private String Address;
	public String sql;
	public static String s_sql;

	// Getter and Setters
	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Addres) {
		Address = Addres;
	}

	public int getId_station() {
		return id_station;
	}

	public void setId_station(int id_station) {
		this.id_station = id_station;
	}

	// Constructeur avec parametres
	public station(int id, String nom, String Addres) {
		super();
		id_station = id;
		Nom = nom;
		Address = Addres;
	}

	public station(int id) {
		id_station = id;
	}
	// Constructeur sans parametres

	public station() {

	}

	@Override
	public String toString() {
		return "station [id_station = " + id_station + "Nom=" + Nom + ", Address=" + Address + "]";
	}

	public static ArrayList<station> lststation() {
		ArrayList<station> st = new ArrayList<station>();
		String Qeury = "select * from station";
		s_sql = Qeury;
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			station s = null;
			while (rs.next()) {
				s = new station(Integer.parseInt(rs.getString("id_station")), rs.getString("Nom"),
						rs.getString("Address"));
				st.add(s);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return st;
	}

	public void fiche() {
		String Qeury = "select * from station where id_station = '" + id_station + "'";
		sql = Qeury;
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			rs.next();
			this.Nom = rs.getString("Nom");
			this.Address = rs.getString("Address");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public String Ajouter() {
		String Qeury = "insert into station(Nom,Address) value('" + this.Nom + "','" + this.Address + "')";
		sql = Qeury;
		ConnectionBD.executerCrud(Qeury);
		return "";
	}

	public String Modifier() {
		String Qeury = "Update  station set Address= '" + this.Address + "', Nom = '" + this.Nom + "' where id_station = "
				+ id_station;
		sql = Qeury;
		ConnectionBD.executerCrud(Qeury);
		return  Qeury;
	}

	public String Supprimer() {
		String Qeury = "delete from  station where id_station = '" + id_station + "'";
		sql = Qeury;
		ConnectionBD.executerCrud(Qeury);
		return "";
	}
}
