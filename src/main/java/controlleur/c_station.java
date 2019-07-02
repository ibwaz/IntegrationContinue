package controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stationMetro.station;

@WebServlet("/c_station")
public class c_station extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameterMap().containsKey("action") && !request.getParameter("action").equals("")
				? request.getParameter("action")
				: "null";
		String id_s = request.getParameter("s_id") == null ? "-999991" : request.getParameter("s_id");
		String Nom = request.getParameter("s_nom") == null ? "" : request.getParameter("s_nom");
		String Desc = request.getParameter("s_addr") == null ? "" : request.getParameter("s_addr");
		station s = new station(Integer.parseInt(id_s), Nom, Desc);
		if (action.equals("supprimer")) {// OK
			s = new station(Integer.parseInt(id_s));
			s.Supprimer();
		}
		if (action.equals("ajouter")) {// OK
			s.Ajouter();
		}
		if (action.equals("modifier")) {// OK
			out.print(s.Modifier());
		} 
		 String[] val = {action,id_s};
		 request.setAttribute("param", val);
		this.getServletContext().getRequestDispatcher("/gestionstation.jsp").forward(request, response);
	}

}
