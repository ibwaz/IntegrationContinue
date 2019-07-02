package PassagersMetro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB_PARAM.ConnectionBD;
import ligneMetro.ligne;

public class passagers {
	private int id_p;
	private int Nombre;
	private ligne Rame;

	public passagers(int id_p, int nombre, ligne rame) {
		super();
		this.id_p = id_p;
		Nombre = nombre;
		Rame = rame;
	}

	public passagers(int id_p) {
		super();
		this.id_p = id_p;
		this.Rame = new ligne();
	}

	public passagers() {
		this.Rame = new ligne();
	}

	public int getId_p() {
		return id_p;
	}

	public void setId_p(int id_p) {
		this.id_p = id_p;
	}

	public int getNombre() {
		return Nombre;
	}

	public void setNombre(int nombre) {
		Nombre = nombre;
	}

	public ligne getRame() {
		return Rame;
	}

	public void setRame(ligne rame) {
		Rame = rame;
	}

	@Override
	public String toString() {
		return "passagers [id_p=" + id_p + ", Nombre=" + Nombre + ", Rame=" + Rame.getId_ligne() + "]";
	}

	public String Ajouter() {
		String Qeury = "insert into passagers(Nombre,Rame) value('" + this.Nombre + "','" + this.Rame.getId_ligne()
				+ "')";
		ConnectionBD.executerCrud(Qeury);
		return "";
	}

	public String Modifier() {
		String Qeury = "Update  passagers set Nombre= '" + this.Nombre + "', Rame = '" + this.Rame.getId_ligne()
				+ "' where id_p = " + id_p;

		ConnectionBD.executerCrud(Qeury);
		return Qeury;
	}

	public String Supprimer() {
		String Qeury = "delete from  passagers where id_p = '" + id_p + "'";

		ConnectionBD.executerCrud(Qeury);
		return "";
	}

	public void fiche() {
		String Qeury = "select * from passagers where id_p = '" + id_p + "'";
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			rs.next();
			this.Nombre = Integer.parseInt(rs.getString("Nombre"));
			this.Rame.setId_ligne(Integer.parseInt(rs.getString("Rame")));
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static ArrayList<passagers> lstpassag() {
		ArrayList<passagers> st = new ArrayList<passagers>();
		String Qeury = "select * from passagers";
		ResultSet rs = ConnectionBD.executerSelect(Qeury);
		try {
			passagers s = null;
			ligne l = null;
			while (rs.next()) {
				l = new ligne(Integer.parseInt(rs.getString("Rame")));
				s = new passagers(Integer.parseInt(rs.getString("id_p")), Integer.parseInt(rs.getString("Nombre")), l);
				st.add(s);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return st;
	}
}
