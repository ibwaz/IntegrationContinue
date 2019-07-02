<%@page import="ligneMetro.ligne"%>
<%@page
	import="stationMetro.station,java.util.ArrayList, java.util.Arrays"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<ligne> cus = ligne.lstligne();
	ArrayList<station> stat = station.lststation();
	ligne l = new ligne(8);
	String[] articles = { "null", "-9999" };
	if (request.getAttribute("param") != null) {
		articles = (String[]) request.getAttribute("param");
	}
	String action = articles[0];
	int id_l = Integer.parseInt(articles[1]);
	if (action.equals("station")) {
		l = new ligne(id_l);
	} else if (action.equals("fiche")) {
		l = new ligne(id_l);
		l.fiche();
	}
	l.charger_correspandance();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="./css/w3.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project 9 - Gestion Ligne Metro</title>
</head>
<body>

	<!-- Barre de navigation MENU -->
	<div class="w3-padding w3-blue w3-large" style="z-index: 4">
		<a href="gestionstation.jsp"
			class="w3-bar-item  btn w3-hide-small w3-hover-white w3-text-white">Gestion
			Stations </a> <a href="gestionligne.jsp"
			class="w3-bar-item btn w3-hide-small  w3-text-white w3-cyan w3-wide">Gestion
			Lignes </a> <a href="gestionConducteur.jsp"
			class="w3-bar-item  btn w3-hide-small  w3-hover-white w3-text-white">Gestion
			Conducteurs</a> <a href="gestionPassagers.jsp"
			class="w3-bar-itembtn btn w3-hide-small  w3-hover-white w3-text-white">Gestion
			Passagers</a> <span class="w3-bar-item w3-left w3-margin-left w3-margin-right"> <img
			src="./images/metro.png" width="30px" height="30px" /></span>
	</div>
	<div class="container w3-right " style="width: 400px;">
		<h2 class="w3-center w3-border">Trajet</h2>
		<table class="w3-table">
			<%
				if (l.getCorrespondance().size() == 0) {
			%>
			<tr>
				<td colspan="2" class="w3-center">No Station Deservies</td>
			</tr>
			<%
				} else {

					for (station sta : l.getCorrespondance()) {
			%>
			<tr>
				<td colspan="2" class="w3-center"><%=sta.getNom()%></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</div>
	<form action='c_ligne' method='post'>
		<div class="w3-margin-top">
			<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
				style="z-index:3;width:200px;" id="mySidebar">

			<div class="w3-bar-block w3-center w3-margin-left">
				<button type='submit' name='action' value='fiche'
					class="w3-bar-item  w3-hover-pale-red w3-padding btn w3-border">Fiche</button>
				<button type='submit' name='action' value='station'
					class="w3-bar-item w3-hover-pale-red w3-padding btn w3-border">Stations</button>

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
				<label>Id Ligne </label> <input type="text" class="form-control"
					name="l_id" placeholder="id" value="<%=l.getId_ligne()%>" required>
			</div>
			<div class="form-group">
				<label>Nom Ligne </label> <input type="text"
					class="form-control" name="s_nom" placeholder="Nom"
					value="<%=l.getNom()%>">
			</div>
			<div class="form-group">
				<label>Description</label> <input class="form-control"
					name="s_descr" placeholder="Description" value="<%=l.getDesc()%>">
			</div>

			<div class="form-group">
				<label>Stations desservies pour mise a jour</label> <select
					class="form-control" multiple="true" name="stations">
					<%
						for (station cc : stat) {
					%>
					<option value="<%=cc.getId_station()%>"><%=cc.getNom()%></option>
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
					<th>ID Ligne</th>
					<th>Nom Ligne Metro</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (cus.size() == 0) {
				%>
				<tr>
					<td colspan="3" class="w3-center w3-red">Pas de lignes à
						afficher..</td>

				</tr>
				<%
					} else {

						for (ligne cc : cus) {
				%>
				<tr>
					<td><%=cc.getId_ligne()%></td>
					<td><%=cc.getNom()%></td>
					<td><%=cc.getDesc()%></td>
				</tr>
				<%
					}
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>