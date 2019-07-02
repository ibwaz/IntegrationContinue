package ligneMetro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.Query;

import DB_PARAM.ConnectionBD;
import stationMetro.station;

public class ligne {
	private int id_ligne;
	private String Nom;
	private String Desc;
	private ArrayList<station> correspondance;
	public String sql;
	public static String s_sql;

	public ligne(int id_ligne, String nom, String desc) {
		super();
		this.id_ligne = id_ligne;
		Nom = nom;
		Desc = desc;
		correspondance = new ArrayList<station>();
	}

	public ligne(int id_ligne) {
		correspondance = new ArrayList<station>();
		this.id_ligne = id_ligne;
	}

	public ligne() {
		correspondance = new ArrayList<station>();
	}

	// Getter and Setters
	public int getId_ligne() {
		return id_ligne;
	}

	public void setId_ligne(int id_ligne) {
		this.id_ligne = id_ligne;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public ArrayList<station> getCorrespondance() {
		return correspondance;
	}

	public void setCorrespondance(ArrayList<station> correspondance) {

		this.correspondance = correspondance;
	}

	public static ArrayList<ligne> lstligne() {
		ArrayList<ligne> st = new ArrayList<ligne>();
		String Qeury = "select * from ligne_m";
		s_sql = Qeury;
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			ligne l = null;
			while (rs.next()) {
				l = new ligne(Integer.parseInt(rs.getString("id_ligne")), rs.getString("Nom"), rs.getString("descr"));
				st.add(l);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return st;
	}

	@Override
	public String toString() {
		String output = "";
		output = "ligne [id_ligne=" + id_ligne + ", Nom=" + Nom + ", Desc=" + Desc + " \n __";
		if (this.correspondance.size() > 0) {
			for (station s : this.correspondance) {
				output += s.getNom() + " __";
			}
		}
		return output;
	}

	public void fiche() {
		String Qeury = "select * from ligne_m where id_ligne = '" + id_ligne + "'";
		sql = Qeury;
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			rs.next();
			this.Nom = rs.getString("Nom");
			this.Desc = rs.getString("descr");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void charger_correspandance() {
		String Qeury = "select * from chemin where id_ligne = '" + id_ligne + "'";
		sql = Qeury;
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			station sigSt = null;
			while (rs.next()) {
				sigSt = new station(Integer.parseInt(rs.getString("id_station")));
				sigSt.fiche();
				this.correspondance.add(sigSt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String Ajouter() {
		String Qeury = "insert into ligne_m(Nom,descr) value('" + this.Nom + "','" + this.Desc + "')";
		ConnectionBD.executerCrud(Qeury);
		Qeury = "select * from ligne_m order by id_ligne desc ";
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			rs.next();
			ligne l = new ligne(Integer.parseInt(rs.getString("id_ligne")));
			for (station st : this.correspondance) {
				Qeury = "insert into chemin(id_ligne,id_station) " + "value('" + l.id_ligne + "','" + st.getId_station()
						+ "')";
				ConnectionBD.executerCrud(Qeury);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Qeury;
	}

	public String Modifier() {
		String Qeury = "Update  ligne_m set descr= '" + this.Desc + "', Nom = '" + this.Nom + "' where id_ligne = "
				+ id_ligne;
		ConnectionBD.executerCrud(Qeury);
		Qeury = "delete from  chemin where id_ligne = '" + id_ligne + "'";
		ConnectionBD.executerCrud(Qeury);
		for (station st : this.correspondance) {
			Qeury = "insert into chemin(id_ligne,id_station) " + "value('" + this.id_ligne + "','" + st.getId_station()
					+ "')";
			ConnectionBD.executerCrud(Qeury);
		}
		return Qeury;
	}

	public String Supprimer() {
		String Qeury = "delete from  ligne_m where id_ligne = '" + id_ligne + "'";
		sql = Qeury;
		ConnectionBD.executerCrud(Qeury);
		Qeury = "delete from  chemin where id_ligne = '" + id_ligne + "'";
		ConnectionBD.executerCrud(Qeury);
		return "";
	}
}
