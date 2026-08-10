@screen
Feature: CSV to Google Sheet

  Scenario: Insert all data in google sheet
    And in Google Sheets I get all items from Factura
    And in Google Sheets I get all Cheltuieli from CSV
    And in Google Sheets I get all Venituri from CSV
    And in Google Sheets I get all PeopleBank from CSV
    And in Google Sheets I get all data from "1qphpzFW-SXp4PRKKUIviSWYX_b7kt3V_4_RO8wT3URs" CSV
    And in Google Sheets I populate Verificare with data from Cheltuieli and Venituri
    And I stop test
