<%@page import="ligneMetro.ligne"%>
<%@page import="conducteurMetro.conducteur"%>
<%@page
	import="stationMetro.station,java.util.ArrayList, java.util.Arrays"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<conducteur> cus = conducteur.lstconducteur();
	ArrayList<ligne> ls = ligne.lstligne();
	conducteur s = new conducteur();
	String[] articles = {"null", "-9999"};
	if (request.getAttribute("param") != null) {
		articles = (String[]) request.getAttribute("param");
	}
	String action = articles[0];
	int id_c = Integer.parseInt(articles[1]);
	if (action.equals("fiche")) {
		s = new conducteur(id_c);
		s.fiche();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="./css/w3.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project 9 - Gestion Conducteur</title>
</head>
<body>

	<!-- Barre de navigation MENU -->
	<div class="w3-padding w3-blue w3-large" style="z-index: 4">
		<a href="gestionstation.jsp"
			class="w3-bar-item  btn w3-hide-small  w3-hover-white w3-text-white">Gestion
			Stations </a> <a href="gestionligne.jsp"
			class="w3-bar-item btn w3-hide-small  w3-hover-white w3-text-white">Gestion
			Lignes </a> <a href="gestionConducteur.jsp"
			class="w3-bar-item  btn w3-hide-small  w3-text-white w3-cyan w3-wide ">Gestion
			Conducteurs</a> <a href="gestionPassagers.jsp"
			class="w3-bar-itembtn btn w3-hide-small  w3-hover-white w3-text-white">Gestion
			Passagers</a> 
			<span class="w3-bar-item  w3-left w3-margin-left w3-margin-right"> <img
			src="./images/metro.png" width="30px" height="30px" />
		</span>
	</div>

	<form action='c_conducteur' method='post'>
		<div class="w3-margin-top">
			<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
				style="z-index:3;width:200px;" id="mySidebar">

			<div class="w3-bar-block w3-center w3-margin-left">
				<button type='submit' name='action' value='fiche'
					class="w3-bar-item  w3-hover-pale-red w3-padding btn w3-border">Fiche</button>
				<button type='submit' name='action' value='ajouter'
					class="w3-bar-item  w3-hover-pale-red w3-padding btn w3-border">Ajouter</button>
				<button type='submit' name='action' value='modifier'
					class="w3-bar-item w3-hover-pale-red w3-padding btn w3-border">Modifier</button>
				<button type='submit' name='action' value='supprimer'
					class="w3-bar-item  w3-hover-pale-red w3-padding btn w3-border">Supprimer</button>

			</div>
			</nav>
		</div>
		<div class='container' style="width: 40%;">
			<div class="form-group">
				<label>Id Conducteur </label> <input type="text"
					class="form-control" name="c_id" value="<%=s.getId_c()%>" required>
			</div>
			<div class="form-group">
				<label>Nom Conducteur </label> <input type="text"
					class="form-control" name="c_nom" value="<%=s.getNom()%>">
			</div>
			<div class="form-group">
				<input type="hidden" id="idLigne"
					value="<%=s.getRame().getId_ligne()%>"> <label>Nom Ligne</label>
				<select id="ligne" class="form-control" name="c_rame">
					<option></option>
					<%
						for (ligne cc : ls) {
					%>
					<option value="<%=cc.getId_ligne()%>">
						<%=cc.getNom()%>
					</option>
					<%
						}
					%>
				</select>

			</div>
		</div>
	</form>
	<div class='container' style="width: 40%;">
		<table class='table'>
			<thead>
				<tr>
					<th>ID Conducteur</th>
					<th>Nom Conducteur</th>
					<th>Nom Ligne</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (cus.size() == 0) {
				%>
				<tr>
					<td colspan="3" class="w3-center w3-red">Pas de Conducteur à
						afficher..</td>

				</tr>
				<%
					} else {

						for (conducteur cc : cus) {
				%>
				<tr>
					<td><%=cc.getId_c()%></td>
					<td><%=cc.getNom()%></td>
					<td>
						<%
							cc.getRame().fiche();
									out.print(cc.getRame().getNom());
						%>
					</td>
				</tr>
				<%
					}
					}
				%>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		var id = document.getElementById("idLigne");
		SelectElement("ligne", id.value);
		function SelectElement(id, valueToSelect) {
			var element = document.getElementById(id);
			element.value = valueToSelect;
		}
	</script>
</body>
</html>