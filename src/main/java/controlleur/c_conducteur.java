package controlleur;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conducteurMetro.conducteur;
import ligneMetro.ligne;

@WebServlet("/c_conducteur")
public class c_conducteur extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameterMap().containsKey("action") && !request.getParameter("action").equals("")
				? request.getParameter("action")
				: "null";
		String c_id = request.getParameter("c_id") == null ? "-999991" : request.getParameter("c_id");
		String Nom = request.getParameter("c_nom") == null ? "" : request.getParameter("c_nom");
		int c_rame = request.getParameterMap().containsKey("c_rame") && !request.getParameter("c_rame").equals("")
				&& !request.getParameter("c_rame").equals("null") ? Integer.parseInt(request.getParameter("c_rame"))
						: -1;
		ligne l = new ligne(c_rame);
		conducteur s = new conducteur(Integer.parseInt(c_id), Nom, l);

		if (action.equals("supprimer")) {// OK
			s = new conducteur(Integer.parseInt(c_id));
			s.Supprimer();
		}
		if (action.equals("ajouter")) {// OK
			s.Ajouter();
		}
		if (action.equals("modifier")) {// OK
			out.print(s.Modifier());
		}
		String[] val = { action, c_id };
		request.setAttribute("param", val);

		this.getServletContext().getRequestDispatcher("/gestionConducteur.jsp").forward(request,
		response);

	}

}
