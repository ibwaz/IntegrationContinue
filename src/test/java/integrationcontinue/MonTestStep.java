package integrationcontinue;

import java.util.ArrayList;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import stationMetro.station;

public class MonTestStep {
private station s;
ArrayList<station> list;
	
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

}
