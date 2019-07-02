package controlleur;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ligneMetro.ligne;
import stationMetro.station;

@WebServlet("/c_ligne")
public class c_ligne extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameterMap().containsKey("action") && !request.getParameter("action").equals("")
				? request.getParameter("action")
				: "null";
		String id_l = request.getParameter("l_id") == null ? "-999991" : request.getParameter("l_id");
		String Nom = request.getParameter("s_nom") == null ? "" : request.getParameter("s_nom");
		String Desc = request.getParameter("s_descr") == null ? "" : request.getParameter("s_descr");
		String[] stations = {};
		if (request.getParameterValues("stations") != null) {
			stations = (String[]) request.getParameterValues("stations");
		}
		out.print(stations.length);
		ligne s = new ligne(Integer.parseInt(id_l), Nom, Desc);
		if (stations.length > 0) {
			station sigSt = null;
			for (int i = 0; i < stations.length; i++) {
				sigSt = new station(Integer.parseInt(stations[i]));
				sigSt.fiche();
				s.getCorrespondance().add(sigSt);
			}
		}
		if (action.equals("supprimer")) {// OK
			s = new ligne(Integer.parseInt(id_l));
			s.Supprimer();
		}
		if (action.equals("ajouter")) {// OK
			s.Ajouter();
		}
		if (action.equals("modifier")) {// OK
			out.print(s.Modifier());
		}
		String[] val = { action, id_l };
		request.setAttribute("param", val);
		out.print(s.toString());
		this.getServletContext().getRequestDispatcher("/gestionligne.jsp").forward(request, response);
	}

}
