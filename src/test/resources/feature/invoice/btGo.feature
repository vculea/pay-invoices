@screen
Feature: As a Customer I pay all my invoices

#  Scenario: Donatii cu destinatie speciala in BTGo
#    And I prepare data for Donatii cu destinatie speciala New from google sheet
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And in BTGo I send Donatii cu destinatie speciala from google sheet

#  Scenario: Sustinere educatie in BTGo
#    And I prepare data for Sustinere educatie from google sheet
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And in BTGo I send Sustinere educatie from google sheet

#  Scenario: Save reports din BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And in BTGo I save report from "Februarie" month

#  Scenario: Plateste orice factura in BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And in BTGo I pay invoices:
#      | fileName | decizia | category      | value    | furnizor            | iban                     | nr | description              |
#      |          |         | TitluriDeStat | 250000.00 | BT Capital Partners | RO96BTRL01301202925690XX | 4  | titluri de stat (4) |
#      |          |         | TitluriDeStat | 48875.00 | BT Capital Partners | RO45BTRL01304202925690XX | 5  | titluri de stat  euro(5) |
#      |          | Materiale Grup | 700   | ASOCIATIA CURSUL ALPHA ROMANIA | RO28BTRLRONCRT0372089601 | 1  | Cursuri pentru grupuri |
#      | Factura116.pdf | Inchinare | 270.00 | AMA DEUM MUSICA SRL | RO80BTRLRONCRT0CU5601301 | AS0727 | Cursuri canto |
#      | Factura230.pdf |         | Inchinare | 1080.00 | THE BEAT PROJECT SRL | RO24BTRLRONCRT0CF5411001 | BP1887 | Cursuri canto |
#      | Factura117.pdf | SchimbDestinatie | 1600.19 | SC BEJAN & PARTNERS TEAM SRL | RO81BTRLRONCRT0535890801 | BEJ0264 | Emitere aviz ISU |
#      | Factura272.pdf |         | Femei    | 592.90 | MICOTEX PROMOTION ADVERTISING SRL | RO72BTRLRONCRT0208589101 | MPA6733 | Cadouri de craciun femei |
#      | Factura39.pdf |         | Femei    | 138.00 | RUNCAN EMIL-ANDREI PERSOANA FIZICA AUTORIZATA | RO40BTRLRONCRT0CO9627201 | REA0011 | pentru invitata |
#      | Factura26.pdf |         | Mentenanta | 650.00 | CLIMATIC GPS SRL | RO58BTRLRONCRT0380394201 | CLPJ1155 | verificarea AC |
#      | Factura15.pdf |         | IesireaBiserica | 3000.00 | PENSIUNEA SEQUOIA SRL | RO69BTRLRONCRT0454970901 | SEQU2052 | cazare cu biserica |
#      | Factura57.pdf |         | Conferinta | 3894.00 | SC SEGRA COM SRL | RO40BTRLRONCRT0P23874101 | FCT0062069 | mancare conferinta |
#      | Factura38.pdf |         | Conferinta | 723.00 | S.C.ROPRINT IMP EXP SRL | RO63BTRL01301202992783XX | CJROP040710 | materiale conferinta |
#      | Factura22.pdf |         | Copiii   | 2000.00 | Fundatia Elpis | RO47BTRLRONCRT0093426204 | FE057 | cazare tabara copii |
#      | Factura14.pdf |         | Diverse  | 498.85 | DIGISIGN SA | RO54BTRL04801202W36621XX | 4370410 | semnatura digitala 3 ani |

#  Scenario: Plateste donatiile in BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And in BTGo I pay invoices:
#      | fileName                  | decizia | category   | value   | furnizor                 | description                                | iban                     |
#      | ContractDeDonatie16.pdf | Decizie comitet52.pdf | Sustinere alte biserici | 20000.00 | Biserica Baptista Emanuel din Beclean | Donatie cf contract donatie 16 din 26.03.2026 | RO17RNCB0039014569740001 |
#      | ContractDeDonatie17.pdf |         | RazeDeSoareOut | 5000.00 | Asociația Raza de soare in sate | Donatie cf contract donatie 17 din 06.04.2026 | RO42RNCB0162121946580001 |
#      | ContractDeDonatie18Or.pdf |         | Alimentare | 7400.00 | Asociația Actiunea Felix | cf contract sponsorizare 59 din 16.04.2026 | RO30CECEBH0143RON0496616 |
#      | Contract de donatie nr10 din 2026.08.08.pdf | Decizie comitet donatie6.pdf | Adolescenti | 8000.00 | Asociația Raza Soarelui-Sunlight | Donatie conform contract donatie 10 din 08.08.2026 | RO23RZBR0000060003200106 |
#      | DispozitieDePlata24.pdf | Decizie comitet donatie7.pdf | DonatiiOut | 4357.30 | Comunitatea Bisericilor Creștine Baptiste Suceava | pentru sinistratii din Suceava-Neamt | RO38RNCB0234037009660015 |
#      | ContractDeDonatie13.pdf | Decizie comitet donatie10.pdf | Femei    | 1500.00 | Asociatia Mops Cluj | Donatie     | RO72BTRLRONCRT0642499201 |
#      | ContractDeDonatie13.pdf |         | Copiii   | 650.00 | Fundatia Romania Pro-Culture | Donatie     | RO58INGB0001008170958910 |
#      | Sustinere familii | 10000 | Asociatia pentru Integritatea Familiei | Donatie pentru Marius Cruceru | RO18BTRLRONCRT0320656501 |
#      | ProVitaOut | 1000  | Fundatia Clinica Pro-vita | Donatie pentru Marsul pt Viata | RO98BTRL01301205R83319XX |
#      | ContractDeDonatie18.pdf | Decizie comitet55.pdf | ProVitaOut | 2000.00 | Fundatia Clinica Pro-vita | Donatie cnf. contract 18 din 10.03.2026 | RO98BTRL01301205R83319XX |
#      |          | RVE      | 2000  | Asociatia RADIO VOCEA EVANGHELIEI sucursala Cluj | donatie     | RO38RNCB0106026613860001 |
#      | Decizie comitet donatie2.pdf | CredoTV  | 10000.00 | ASOCIATIA CREDO TELEVISION NETWORK | donatie     | RO28RNCB0074029224060001 |
#      |          |         | SeerRomania | 500   | Fundatia Seer Romania | donatie     | RO65BTRL03201205226788XX |
#      |          |         | Comunitate | 1000  | Comunitatea Bisericilor Crestine Baptiste Cluj    | contributii membrale | RO02RNCB0106026606820001 |
#      |          |         | Comunitate | 1000  | Uniunea Bisericilor Crestine Baptiste din Romania | donatie              | RO26RNCB0072049718910001 |

#  Scenario: Plateste factura de utilitati in BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And in BTGo I pay invoices:
#      | fileName      | category |
#      | Factura91.pdf | Apa      |
#      | FacturaGazFeb.pdf | Gaz      |
#      | Factura90.pdf | Gunoi    |

#  Scenario: Generate extras conturi in BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And I generate extras from all in BTGo

#  Scenario: Move all from Cont Current to Depozit in BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And I move all from Cont Current to Depozit in BTGo

#    Scenario: Create depozit from Cont de Economii in BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And I create depozit from Cont de Economii in BTGo

#  Scenario: Plata decont in BTGo
#    And I open url "https://goapp.bancatransilvania.ro/app/auth/login"
#    And I login in BTGo
#    And in BTGo I pay deconts:
#      | decont       | category    |
#      | Decont1.pdf | Alimentare |
#      | Decont11.pdf | Copiii   |
#      | Decont12.pdf | Adolescenti |
#      | Decont4.pdf | Sanitare    |
#      | Decont8.pdf | Femei       |
#      | Decont22.pdf | Decor    |
#      | Decont9.pdf | Tehnic   |

#  Scenario: Test
#    And I upload file "DovadaPlataCopiiiIulie.pdf"
#    And I read pdf "C:\Users\vculea\Desktop\ExtrasDeCont.pdf"

#  Scenario: Add facturile sau bonuri in google sheets
#    And I add in Facturi or Bonuri in google sheet:
#      | fileName      | decizia | decont | type    | plata | category  | data       | value  | description           |
#      | DovadaPlataSomething NewDecembrie.pdf   | Dovada | Cont  | SomethingNewOut  | 13.12.2024 | 371   | plata       |
#      | DovadaPlataTeenChallengeDecembrie.pdf  | Dovada | Cont  | TeenChallengeOut | 13.12.2024 | 100   | plata       |
#      | DovadaPlataCasaFilipDecembrie.pdf      | Dovada | Cont  | CasaFilipOut     | 13.12.2024 | 200   | plata       |
#      | DovadaPlataTanzaniaDecembrie.pdf       | Dovada | Cont  | TanzaniaOut      | 13.12.2024 | 140   | plata       |
#      | DovadaPlataCaminulFelixDecembrie.pdf   | Dovada | Cont  | FelixOut         | 13.12.2024 | 216   | plata       |
#      | DispozitieDePlata66.pdf | Decizie comitet donatie7.pdf |        | Dovada  | Cash  | DonatiiOut | 24.08.2026 | 4357.30 | sustinere misionarilor din Nepal (1000$) |
#      | DispozitieDePlata16.pdf | Decizie comitet premiere participanti olimpiada.pdf |             | Dovada  | Cash  | Copiii     | 01.06.2026 | 200    | premium olimpiada Estera, Sofia        |
#      | DispozitieDePlata17.pdf | Decizie comitet premiere participanti olimpiada.pdf |             | Dovada  | Cash  | Copiii     | 01.06.2026 | 300    | premium olimpiada Siena, Titus, Andrei |
#      | DispozitieDePlata20.pdf | Decizie comitet premiere participanti olimpiada.pdf |        | Dovada  | Cash  | Copiii     | 08.06.2026 | 200    | premium olimpiada David, Ionatan |
#      | DispozitieDePlata25.pdf | Decizie comitet donatie9.pdf |        | Dovada | Cash  | Sustinere alte biserici | 25.09.2026 | 1000.00 | donatie pentru familia Husar |
#      | DovadaDepuneriInCont1.pdf |         |        | Dovada  | Cash  | In Cont    | 02.03.2026 | 1550.00 | depunere in cont  |
#      | DovadaRetragere1.pdf |         |        | Dovada | Cont  | In Cont  | 09.01.2026 | 2000.00 | retragere din cont |
#      | Factura152.pdf | Factura | Cash  | Dotari   | 17.11.2024 | 449.99 | Router 3         |
#      | Factura53.pdf |         |        | Factura | Cash  | Mentenanta | 03.03.2026 | 63.15  | Adaptor audio               |
#      | Factura54.pdf |         |        | Factura | Cash  | Mentenanta | 04.03.2026 | 88.00  | Bara silicon                |
#      | Factura73.pdf | Factura | Cash  | Femei    | 02.04.2026 | 40.00  | Panglica Momco            |
#      | Factura45.pdf |         |        | Factura    | Cont  | Femei      | 01.03.2026 | 1630.00 | Produse pentru intalnirea femeilor |
#      | Factura48.pdf |         |        | Bon cu CUI | Cont  | Femei      | 28.02.2026 | 160.00  | Flori invitata                     |
#      | Factura85.pdf |         |        | Factura | Cash  | Tehnic   | 07.04.2026 | 65.97  | Cablu USB-c      |
#      | Factura86.pdf |         |        | Factura | Cash  | Tehnic   | 07.04.2026 | 181.50 | Incarcator USB-c |
#      | Factura73.pdf |         |        | Factura | Cont  | Dotari   | 24.03.2026 | 299.99 | Camera de supraveghere         |
#      | Factura34.pdf |         |        | Factura    | Cont  | Recunostinta | 14.02.2026 | 200.00  | flori pentru invitata            |
#      | Factura62.pdf |         |        | Factura    | Cash  | Conferinta | 16.03.2026 | 182.40 | cheltuieli cu invitat (Florin) |
#      | Factura64.pdf |         |        | Bon cu CUI | Cont  | Conferinta | 12.03.2026 | 259.00 | Mancare cu invitat             |
#      | Factura65.pdf |         |        | Bon cu CUI | Cont  | Conferinta | 12.03.2026 | 280.65 | Transport la Braila            |
#      | Factura92.pdf |         |        | Factura | Cont  | Conferinta | 10.04.2026 | 480.30 | Transport de la Braila |
#      | Factura90.pdf |            |        | Factura | Cash  | Femei    | 30.04.2026 | 149.75 | Plasturi    |
#      | Factura94.pdf | ExtrasCard4.pdf | Decont2.pdf | Factura | Cash  | IesireaBiserica | 01.05.2026 | 72.88 | Produse pentru iesire |
#      | Factura92.pdf |            |        | Chitanta | Cash  | IesireaBiserica | 02.05.2026 | 3500.00 | Donatie Fundatia Emanuel |
#      | DispozitieDePlata66.pdf | Decizie comitet48.pdf |        | Factura | Cash  | Invitati | 04.01.2026 | 200.00 | Teo Cotrau   |
#      | DispozitieDePlata67.pdf | Decizie comitet48.pdf |        | Factura | Cash  | Invitati | 05.01.2026 | 600.00 | Teofil Mihoc |
#      | DispozitieDePlata68.pdf | Decizie comitet48.pdf |        | Factura | Cash  | Invitati | 07.01.2026 | 700.00 | Dani Suciu   |
#      | DispozitieDePlata69.pdf | Decizie comitet48.pdf |        | Factura | Cash  | Invitati | 09.01.2026 | 900.00 | Emi Ciupe    |
#      | DispozitieDePlata70.pdf | Decizie comitet48.pdf |        | Factura | Cash  | Invitati | 10.01.2026 | 600.00 | Claudiu Pop  |
#      | DispozitieDePlata81.pdf |         |        | DdP  | Cash  | Adolescenti | 22.03.2026 | 250.00 | pentru Emi Ciupe |
#      | Factura94.pdf |         |        | Factura | Cont  | Alimentare | 15.04.2026 | 770.00 | Produse de la Panemar |
#      | Factura5.pdf         |         |        | Factura | Cont  | Alimentare | 09.01.2026 | 535.53  | Pizza              |
#      | Factura282.pdf          |         |        | Factura | Cont  | Alimentare   | 27.12.2026 | 117.95  | Micro Revelion |
#      | Factura283.pdf          |         |        | Factura | Cont  | Alimentare   | 28.12.2026 | 3475.23 | Micro Revelion |
#      | Factura284.pdf          |         |        | Factura | Cont  | Recunostinta | 27.12.2026 | 205.00  | Micro Revelion |
#      | Factura37.pdf |         | Decont7.pdf | Bon cu CUI | Cont  | Copiii   | 21.02.2026 | 52.45 | Materiale grupa copii (Dani) |
#      | Factura78.pdf |         |        | Factura | Cont  | Copiii     | 03.04.2026 | 674.67 | Premii copii       |
#      | Factura82.pdf |         |        | Factura | Cont  | Copiii     | 07.04.2026 | 246.26 | Pentru copii       |
#      | Factura83.pdf |         |        | Factura | Cont  | Copiii     | 07.04.2026 | 181.09 | Pentru copii       |
#      | Factura84.pdf |         |        | Factura | Cont  | Copiii     | 04.04.2026 | 35.00  | Pentru copii       |
#      | Factura274.pdf |         |        | Factura | Cont  | Recunostinta | 19.12.2026 | 232.09 | Produse     |
#      | Factura275.pdf |         |        | Factura | Cont  | Recunostinta | 20.12.2026 | 210.77 | Produse     |
#      | Factura276.pdf |         |        | Factura | Cont  | Recunostinta | 20.12.2026 | 95.27  | Produse     |
#      | Factura70.pdf |         | Decont12.pdf | Factura    | Cont  | Adolescenti | 13.03.2026 | 338.39 | Produse (Dani)                 |
#      | Factura71.pdf |         | Decont12.pdf | Bon cu CUI | Cont  | Adolescenti | 13.03.2026 | 255.04 | Produse (Dani)                 |
#      | Factura262.pdf |         |        | Factura    | Cont  | Adolescenti | 09.12.2026 | 120.93  | Produse                 |
#      | Factura18.pdf |         | Decont3.pdf | Bon cu CUI | Cont  | Adolescenti | 06.02.2026 | 400.00 | Activitati adolescenti     |
#      | Factura248.pdf |         | Decont22.pdf | Factura | Cont  | Decor    | 07.12.2026 | 284.00 | Instalatii led (Dana Copaciu) |
#      | Factura95.pdf |         |        | Factura | Cash  | Deplasari | 19.04.2026 | 799.99 | Drum Capalna (Florin) |
#      | Factura19.pdf |         | Decont4.pdf | Factura    | Cash  | Sanitare    | 07.02.2026 | 199.07 | Produse sanitare (Andreea) |
#      | Factura67.pdf |         |              | Factura    | Cash  | Sanitare    | 16.03.2026 | 271.04 | Prosoape (Doru)                |
#      | Factura96.pdf |         |        | Factura | Cash  | Sanitare  | 16.04.2026 | 380.83 | Produse (Doru)        |
#      | Factura79.pdf |         |        | Factura | Cash  | Alimentare | 03.04.2026 | 592.42 | Mancare comitet    |
#      | Factura80.pdf |         |        | Factura | Cont  | Alimentare | 02.04.2026 | 545.14 | Produse alimentare |
#      | Factura81.pdf |         |        | Factura | Cont  | Alimentare | 02.04.2026 | 373.00 | Pizza comitet      |
#      | Factura74.pdf |         |        | Factura | Cont  | Diverse  | 26.03.2026 | 1381.82 | Cod Lei        |
#      | Factura75.pdf |         |        | Factura | Cont  | Diverse  | 26.03.2026 | 100.00  | Zoom (54.45$)  |
#      | Factura76.pdf |         |        | Factura | Cont  | Diverse  | 26.03.2026 | 100.00  | Zoom (129.96$) |
#      | Factura198.pdf |         |        | Factura | Cont  | Sanitare | 11.09.2026 | 1117.62 | Produse pentru curatenie |
#      | Factura21.pdf |         |        | Factura | Cont  | Tiparituri | 06.02.2026 | 501.00 | Rencarcarea cartuselor |
#      | Factura97.pdf |         |        | Factura | Cont  | Femei     | 17.04.2026 | 177.00 | Produse pentru Momco  |
#      | Factura250.pdf |         | Decont23.pdf | Factura | Cont  | Femei    | 06.12.2026 | 401.93 | Produse pentru Femei (A.Sainiuc) |
#      | Factura247.pdf          |         |        | Bon cu CUI | Cash  | Femei         | 07.12.2026 | 122.97  | Produse pentru Femei   |
#      | Factura32.pdf |         |        | Factura    | Cont  | Alimentare   | 15.02.2026 | 1272.07 | Pizza pentru intalnirea din 15   |
#      | Factura167.pdf |            |        | Factura | Cash  | Sanitare    | 10.08.2026 | 99.05  | Domestos (Doru)             |
#      | Factura234.pdf |         | Decont20.pdf | Bon cu CUI | Cont  | Adolescenti | 06.11.2026 | 62.90  | Produse adolescenti |
#      | Factura242.pdf |         |        | Factura | Cont  | Adolescenti | 05.12.2026 | 99.55 | Mancare adolescenti |
#      | Factura211.pdf |         | Decont13.pdf | Factura | Cash  | Adolescenti | 18.09.2026 | 236.82 | Mancare adolescenti (Damaris) |
#      | Factura77.pdf | Factura    | Cash  | Comunitate  | 03.04.2026 | 400.00 | Slujire pentru comunitate (Florin) |
#      | Factura77.pdf |         |        | Factura | Cont  | Femei    | 28.01.2026 | 307.84 | Produse pentru Momco |
#      | Factura115.pdf          | ExtrasCard11.pdf                                    |        | Factura | Cont  | Femei      | 04.06.2026 | 735.00 | Produse pentru Momco             |
#      | Factura63.pdf |         |        | Bon cu CUI | Cash  | Tineri     | 16.03.2026 | 135.86 | produse tineri (Ovidiu)        |
#      | Factura30.pdf |         |        | Bon cu CUI | Cash  | Copiii       | 13.02.2026 | 60.00   | produse pentru grupa (Valentina) |
#      | Factura175.pdf | Bon cu CUI | Cash  | Adolescenti | 18.12.2024 | 69.62  | Produse pentru adolescenti (Patri) |
#      | Factura135.pdf | ExtrasCard18.pdf |        | Factura | Cont  | Adolescenti | 12.07.2026 | 3500.07 | Produse pentru adolescenti |
#      | Factura152.pdf | ExtrasCard25.pdf |        | Factura | Cont  | Adolescenti | 13.07.2026 | 520.70 | Produse pentru tabara de adolescenti |
#      | Factura165.pdf |            |        | Factura | Cash  | Adolescenti | 11.08.2026 | 567.07 | Transport sioniada (Florin) |
#      | Factura166.pdf |            |        | Factura | Cash  | Adolescenti | 16.08.2026 | 596.04 | Transport sioniada (Florin) |
#      | Factura168.pdf |            |        | Factura | Cash  | Adolescenti | 09.08.2026 | 584.98 | Transport sioniada (Florin) |
#      | Factura143.pdf |                  |        | Bon cu CUI | Cont  | Adolescenti | 19.07.2026 | 135.89 | Decont transport adolescenti         |
#      | Factura161.pdf |            |        | Factura | Cont  | Adolescenti | 14.07.2026 | 131.69 | Produse pentru tabara de adolescenti |
#      | Factura136.pdf |            |        | Bon cu CUI | Cont  | Adolescenti | 15.07.2026 | 114.52 | Decont transport adolescenti |
#      | Factura72.pdf |         |        | Factura | Cash  | Tineri   | 23.03.2026 | 70.56  | Produse pentru tineri (Ovidiu) |
#      | Factura128.pdf | ExtrasCard16.pdf |        | Bon cu CUI | Cash  | Tineri   | 16.12.2024 | 646.00 | Produse pentru tineri (Ovidiu) |
#      | Factura251.pdf |         |        | Factura | Cash  | Tineri   | 08.12.2026 | 315.99 | Produse pentru tineri (Ovidiu) |
#      | Factura2000.jpeg | Factura | Cont  | Sustinere familii | 10.04.2026 | 2000.00 | Factura de curent Husar Alexandru |
#      | SCRISOARE DONATIE  09.12.2024.pdf | Donatie | Cont  | Diverse  | 08.11.2024 | 109.48 | Hartie A4   |
#      | BibileProject2024.pdf | Donatie | Cash  | BibleProject | 28.12.2024 | 109.48 | Donatie     |
#      | Factura163.pdf |            |        | Factura | Cont  | Pata RatOut | 04.08.2026 | 2178.64 | Materiale pentru bierica Cantoanelor |