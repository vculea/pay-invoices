@screen
Feature: Transcrieri

  Scenario: Transcrieri
    And I open url "https://transcrieri.evp-oradea.ro/rezervare"
    And I make transcriere
      | file       | email              | name           | nr     | data       | tara    |
      | Tamara.pdf | tamara1@givmail.com | Pidoima Tamara | 138202 | 21.03.2023 | Ucraina |
#  myIP: 188.24.2.62
#  myIpMobile: 82.77.155.138, 04.07.2025
  # myIPTibi: 188.27.128.3, 04.07.2025

