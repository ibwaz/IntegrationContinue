package conducteurMetro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB_PARAM.ConnectionBD;
import PassagersMetro.passagers;
import ligneMetro.ligne;
import stationMetro.station;

public class conducteur {
	private int id_c;
	private String Nom;
	private ligne Rame;

	@Override
	public String toString() {
		return "conducteur [id_c=" + id_c + ", Nom=" + Nom + ", Rame=" + Rame.getId_ligne() + "]";
	}

	public int getId_c() {
		return id_c;
	}

	public void setId_c(int id_c) {
		this.id_c = id_c;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public ligne getRame() {
		return Rame;
	}

	public void setRame(ligne rame) {
		Rame = rame;
	}

	public conducteur(int id_c, String nom, ligne rame) {
		super();
		this.id_c = id_c;
		Nom = nom;
		Rame = rame;
	}

	public conducteur(int id_c) {
		this.id_c = id_c;
		this.Rame = new ligne();
	}

	public conducteur() {
		this.Rame = new ligne();
	}
	// __________________________________

	public String Ajouter() {
		String Qeury = "insert into conducteur(Nom,Rame) value('" + this.Nom + "','" + this.Rame.getId_ligne() + "')";
		ConnectionBD.executerCrud(Qeury);
		return "";
	}

	public String Modifier() {
		String Qeury = "Update  conducteur set Nom= '" + this.Nom + "', Rame = '" + this.Rame.getId_ligne()
				+ "' where id_c = " + id_c;

		ConnectionBD.executerCrud(Qeury);
		return Qeury;
	}

	public String Supprimer() {
		String Qeury = "delete from  conducteur where id_c = '" + id_c + "'";

		ConnectionBD.executerCrud(Qeury);
		return "";
	}

	public void fiche() {
		String Qeury = "select * from conducteur where id_c = '" + id_c + "'";
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			rs.next();
			this.Nom = rs.getString("Nom");
			this.Rame.setId_ligne(Integer.parseInt(rs.getString("Rame")));
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static ArrayList<conducteur> lstconducteur() {
		ArrayList<conducteur> st = new ArrayList<conducteur>();
		String Qeury = "select * from conducteur";
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			conducteur s = null;
			ligne l = null;
			while (rs.next()) {
				l = new ligne(Integer.parseInt(rs.getString("Rame")));
				s = new conducteur(Integer.parseInt(rs.getString("id_c")), rs.getString("Nom"), l);
				st.add(s);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return st;
	}
}
