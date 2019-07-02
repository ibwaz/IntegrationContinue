package controlleur;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PassagersMetro.passagers;
import conducteurMetro.conducteur;
import ligneMetro.ligne;
import stationMetro.station;

@WebServlet("/c_passagers")
public class c_passagers extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameterMap().containsKey("action") && !request.getParameter("action").equals("")
				? request.getParameter("action")
				: "null";
		String p_id = request.getParameter("c_id") == null ? "-999991" : request.getParameter("c_id");
		String c_nbr = request.getParameter("c_nbr") == null ? "-999991" : request.getParameter("c_nbr");

		int c_rame = request.getParameterMap().containsKey("c_rame") && !request.getParameter("c_rame").equals("")
				&& !request.getParameter("c_rame").equals("null") ? Integer.parseInt(request.getParameter("c_rame"))
						: -1;
		ligne l = new ligne(c_rame);
		passagers s = new passagers(Integer.parseInt(p_id), Integer.parseInt(c_nbr), l);

		if (action.equals("supprimer")) {// OK
			s = new passagers(Integer.parseInt(p_id));
			s.Supprimer();
		}
		if (action.equals("ajouter")) {// OK
			s.Ajouter();
		}
		if (action.equals("modifier")) {// OK
			out.print(s.Modifier());
		}
		String[] val = { action, p_id };
		request.setAttribute("param", val);

		this.getServletContext().getRequestDispatcher("/gestionPassagers.jsp").forward(request, response);

	}

}
