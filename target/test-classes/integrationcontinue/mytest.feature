Feature: Tester mon application

  Background: 
    Given Ma station existe

  Scenario: Recuperation d'une station via son id
    When Recuperation de la station qui a l'id 1
    Then Le resultat doit etre la meme station souhaitee ou null
    
  Scenario: Recuperation de la liste des stations
    When Recuperation de la liste des stations
    Then Le resultat doit etre la liste des stations superieur a zero