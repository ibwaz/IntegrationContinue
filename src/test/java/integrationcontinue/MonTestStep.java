package integrationcontinue;

import java.util.ArrayList;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import stationMetro.station;
import conducteurMetro.conducteur;
import ligneMetro.ligne;

import PassagersMetro.passagers;

public class MonTestStep {
private station s;
private conducteur c;
private ligne l;
private passagers p;
ArrayList<station> list;
ArrayList<conducteur> liste;
ArrayList<ligne> ligne;
ArrayList<passagers> passagers;
	@Given("Ma station existe")
	public void ma_station_existe() {
	    s = new station();
	}

	@When("Recuperation de la station qui a l'id {int}")
	public void recuperation_de_la_station_qui_a_l_id(Integer int1) {
	    s.setId_station(int1);
	    s.fiche();
	}

	@Then("Le resultat doit etre la meme station souhaitee ou null")
	public void le_resultat_doit_etre_la_meme_station_souhaitee_ou_null() {
	    Assert.assertTrue(!s.getNom().equals(""));
	}
	
	//senario de la liste des stations
	@When("Recuperation de la liste des stations")
	public void recuperation_de_la_liste_des_stations() {
	    list = s.lststation();
	}

	@Then("Le resultat doit etre la liste des stations superieur a zero")
	public void le_resultat_doit_etre_la_liste_des_stations_superieur_a_zero() {
		Assert.assertTrue(list.size()>0);
	}
	
	
	
	
	@Given("Mon conducteur existe")
	public void mon_conducteur_existe() {
	    c = new conducteur();
	}
	//Scenario de la liste des conducteurs
	@When("Recuperation de la liste des conducteurs")
	public void recuperation_de_la_liste_des_conducteurs() {
	    liste = c.lstconducteur();
	}

	@Then("Le resultat doit etre la liste des conducteurs superieur a zero")
	public void le_resultat_doit_etre_la_liste_des_conducteurs_superieur_a_zero() {
		Assert.assertTrue(liste.size()>0);
	}
	
	
	
	
	@Given("Ma ligne existe")
	public void ma_ligne_existe() {
	    l = new ligne();
	}
	@When("Recuperation de la liste des metros")
	public void recuperation_de_la_liste_des_metros() {
	    // Write code here that turns the phrase above into concrete actions
		ligne = l.lstligne();
	}

	@Then("Le resultat doit etre la liste des metros superieur a zero")
	public void le_resultat_doit_etre_la_liste_des_metros_superieur_a_zero() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(ligne.size()>0);
	}

	
	
	
	@Given("Mes passagers existe")
	public void mes_passagers_existe() {
	    p = new passagers();
	}
	@When("Recuperation de la liste des passagers")
	public void recuperation_de_la_liste_des_passagers() {
		passagers = p.lstpassag();
	}

	@Then("Le resultat doit etre la liste des passagers superieur a zero")
	public void le_resultat_doit_etre_la_liste_des_passagers_superieur_a_zero() {
		Assert.assertTrue(passagers.size()>0);
	}
}
