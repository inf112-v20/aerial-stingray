## Obligatorisk oppgave 3 av Aerial-stingray

### Team og Prosjekt

Tidligere har vi kommentert at vi ikke har fått tatt i bruk rollene vi tildelte, og dette er fortsatt tilfelle. Den eneste rollen som har blitt brukt til det den var tiltenkt, er kundekontakt. Utenom dette har det ikke vært klare skiller mellom rollene. Med et kritisk og reflekterende blikk har vi derfor kommet frem til at vi vil endre reollene for denne sprinten, og dermed redusere det ned til tre roller; teamlead, kundekontakt og kode auditør. Disse rollene vil bli byttet på, mens resten vil ha tittelen utviklere. 

Uavhengig av dette føler vi fortsatt at teamet fungerer veldig bra. Vi er fornøyd med valgene vi har tatt, og har forbedret oss på våre tidligere punkter. Vi har dermed blitt flinkere til å møtes  - selv om ikke nødvendigvis alle kan, og vi har blitt flinkere til å fordele oppgaver.  Kommunikasjonen har alltid vært bra, og blir bare bedre også. Selv om det var god flyt fra begynnelsen av, kan det merkes at vi er blitt mer komfortable med hverandre som et team. 

#### Forbedringspunkter til neste gang: 

1. Bruke de endrede rollene
2. Bli flinkere til å se over alt vi pusher - kjøre testene før vi pusher

#### Skjermdump av project board etter første sprintmøte:

[![Skjermbilde-2020-03-11-kl-09-18-55.png](https://i.postimg.cc/XvzLmSFV/Skjermbilde-2020-03-11-kl-09-18-55.png)](https://postimg.cc/f3YYSPBF)


Som dere kan se har vi noen issues i In progress og noen i To do. Grunnen til at det allerede befinner seg issues i In Progress er fordi vi har møttes regelmessig mellom obligatorisk oppgave 2 og 3 for å kunne jobbe videre med prosjektet. Issuesene i To Do er oppgavene vi bestemte oss for skulle løses før neste release. Her har vi valgt å prioritere å implementere programkort i spillet vårt, ettersom vi føler det er en viktig del av spillets funksjon. Vi vil også fokusere på at spillet kan gjennomføre en fase. Dette føler vi vil gi en god gang i spillet. 


### Krav

I forrige sprint fokuserte vi på å grafisk forbedre spillet vårt, samt lage et event-system, som senere gjorde det lettere å legge til ulike events i spill-logikken. Derfor fikk vi også implementert hull i brettet, backup, lagt til normal-samlebånd, express-samlebånd, tannhjul og vegger. I mellomtiden har vi også implementert menyskjerm og programkort. Vi begynner dermed å komme på god vei, men er fortsatt en del som gjenstår. Kravene vi dermed har prioritert for denne sprinten inkludert det vi har gjort i mellomtiden er:

* Kortstokk
* Prioritering på kortene
* Gjennomføre en fase
* Shuffle kortene
* Velge kort
* Bekrefte kort
* En spiller kan dø
* Legge til skiftenøkkel/ Legge til skiftenøkkel og hammer
* Spiller mister ett liv når den faller utenfor brettet.
* Implementere når en spiller vinner



#### Brukerhistorier, arbeidsoppgaver og akseptansekriterier:
_Disse vil også befinne seg under issues i projectboardet vårt._

_**Legge til programkort.**_

Brukerhistorie: Som spiller trenger jeg programkort for å kunne flytte spilleren min.

Arbeidsoppgave: Lage en ny package for kortstokken; cards. Lage et enum for alle korttypene vi har. Deretter en egen klasse for hvert programkort hvor vi implementerer en konstruktør, og get() metode for både type og prioritet. Så lage en klasse for hele kortstokken. Når designet på kortene er laget, implementeres dette i klassene.

Akseptansekriterie: Hvis spilleren velger programkort, skal roboten flytte seg etter kriteriene på disse.

_**Bekrefte kort.**_

Brukerhistorie: Som spiller trenger jeg å bekrefte kort, for å kunne bevege spilleren min. 

Arbeidsoppgave: Lag en ny metode i RoboRally som bekrefter kort. Dette gjøres ved å sjekke at spilleren har valgt riktig antall kort. Hvis dette er tilfelle så kjører den execute card metoden som utfører trekket beskrevet i kortet. Dette styres også av en metode laget i Player med samme navn, som bruker en switch statement som gir de ulike casene av hvilke type kort vi har. 

Akseptansekriterie: Hvis spiller bekrefter kort skal den ikke kunne endre kort for den runden. 

_**Velge kort**_

Brukerhistorie: Som spiller trenger jeg å kunne velge kort, for å selv kunne styre roboten min.

Arbeidsoppgave: Oppdaterer RoboRally med metoder som displayer hvilke kort brukeren kan velge, legger kort som brukeren velger i en stack, men som også fjerner fra stack hvis brukeren velger bort kortet. 

Akseptansekriterie: Hvis spiller velger kort i en viss rekkefølge, skal den utføres i den rekkefølgen samme runde.

_**En spiller kan dø.**_

Brukerhistorie: Som spiller trenger jeg at spilleren min kan dø, for å gjøre spillet mer spennende.

Arbeidsoppgave: Lag en egen klasse som heter LoseScreen. Implementer den slik at når spilleren dør vil man komme til en egen skjerm. Deretter lag en metode i Player som flytter spilleren til LoseScreen når spilleren har mistet 3 liv.

Akseptansekriterie: Hvis spilleren mister 3 liv skal hen bli omdirigert til en egen LoseScreen.

_**Implementere når spilleren vinner**_

Brukerhistorie: Som spiller trenger jeg at flaggene skal samles i riktig rekkefølge for å kunne planlegge mine trekk.

Arbeidsoppgave: Etter at flaggene er implementert og fungerer som de skal - vil vi opprette en metode som sjekker at når spilleren har alle flaggene, så vil spilleren vinne. Implementerer dette i klassen Player.

Akseptansekriterie: Hvis spilleren har riktig rekkefølge på flagg når den står på siste flagg, så vil spilleren vinne.

_**Legge til laser**_

Brukerhistorie: Som spiller trenger jeg at lasere fungerer som de skal for å kunne planlegge trekkene mine.

Akseptansekriterie: Hvis en brikke står i rekkevidden til en laser vil brikken ta en skade.

Arbeidsoppgave: Lager et eget object-layer for lasere i Tiles. Henter ut objekt typen til Lasers ved libgdx med getObjects() ved map.getLayers().get(“OLaser”).getObjects. Implementerer klassene: Board.

_**Legge til skiftenøkkel.**_

Brukerhistorie: Som spiller trenger jeg at skiftenøkkel fungerer som det skal, for å kunne planlegge trekkene mine.

Arbeidsoppgave: Legger til skiftenøkkel på brettet i Tiled og legger objektet inn med typen Single_Wrench under object layer OEvents. Henter ut objektet i event-systemet tidligere laget, og sjekker om spilleren har skade, og hvis den har det skal det fjernes en skade. Implementerer klassene: Player og EventHandler.

Akseptansekriterie: Hvis brikken står på en skiftenøkkel, så vil det fjernes en skade fra brikken. Hvis brikken ikke har tatt noe skade, vil ingenting skje.

_**Legge til hammer og skiftenøkkel tilen.**_

Brukerhistorie: Som spiller trenger jeg at skiftenøkkel og hammer fungerer som det skal, for å kunne planlegge trekkene mine.

Arbeidsoppgave: Legger til skiftenøkkel og hammer tilen på brettet i Tiled og legger objektet inn med typen Hammer_Wrench under object layer i OEvents. Henter ut objektet i event-systemet tidligere laget, og sjekker om spilleren har skade, og hvis den har det skal det fjernes en skade. Spilleren skal også bli gitt et option card. Implementerer klassene: Player og EventHandler.

Akseptansekriterie: Hvis brikken står på en skiftenøkkel, så vil det fjernes en skade fra brikken og gitt et option card. Hvis brikken ikke har tatt noe skade, vil ingenting skje.

_**Meny Skjerm.**_

Brukerhistorie: Som spiller trenger jeg menyskjerm for å lettere kunne navigere meg gjennom spillet.

Arbeidsoppgave: Oppretter egne klasser for GameScreen, MenuScreen, LoseScreen og WinScreen. Hver av klassene skal inneholde designlogikken til deres navngitte skjerm.

Akseptansekriterie: Hvis spilleren trykker på en navngitt knapp, skal den ta spilleren dit det er ment den skal føre til.

_**Legge til checkpoints.**_

Brukerhistorie: Som spiller vil jeg at brikken skal stå på høyest oppnådde flagget etter man mister et liv, slik at man ikke må tilbake til start hele tiden.

Arbeidsoppgave: Gjør at backup blir satt til det høyeste flagget spilleren har fått.

Akseptansekrav: Om spilleren tar et flagg og mister et liv senere, skal spilleren fortsette fra flagget.

_**Legg til player skin.**_

Brukerhistorie: Som spiller trenger jeg player skins for å kunne lett skille hvilken brikke som er min og hvilken som er motstander.

Arbeidsoppgave: Opprette .png fil for hver retning for hver farge av de forskjellige player skinsene.

Akseptansekriterie: Hvis spilleren velger en farge til brikken, så er brikken med den valgte fargen spillerens robot.

_**Stokke kortene.**_

Brukerhistorie: Som spiller trenger jeg at kortene blir stokket, slik at jeg får ulike kort å velge fra.

Arbeidsoppgave: Lag en metode i RoboRally som sjekker hvilke kort som er brukt og som legger de til i bunken igjen og stokker dem. Metoden skal også oppdatere slik at listen som holder kortene som er valgt blir tom. 

Akseptansekriterie: Hvis spilleren har valgt og bekreftet kort, skal nye kort displayes neste runde. 


#### Oppdatert mvp - liste: 

* **Trenger et spillbrett**
* **Trenger en spiller**
* **Visuelle illustrasjon av hvor man befinner seg på brettet**
* **Ta skade**
* **Plukke opp flagg**
* **En spiller dør etter 3 tapte liv - game over**
* **Avslutte spillet, enten ved at alle dør eller at noen vinner**
* **Man vinner spillet ved når man besøker siste flagg og man har de andre flaggene**
* **Kortstokk**
* **Når det er tomt for kort – shuffle kortene**
* **Vegger**
* **Events** : Fungerende Samlebånd, tannhjul, lasere, hull
* **Dør når man havner utenfor brettet**
* **Back-up**
* **Velge kort**
* **Bekrefte kort**
* **Man må kunne spille en komplett runde**
* Prioritering på kortene
* Rekkefølge på eventene
* Man må kunne skyte andre spillere innen rekkevidde med laser som peker rett frem
* Skademekanisme: Justere antall kort basert på skade
* Dytte andre roboter
* Gjennomføre en fase
* Flytte roboten
* Multiplayer vennlig

Mvp listen er så og si lik tidligere mvp liste, men med noen ekstra punkter og noen mer utfylte punkter. De uthevede punktene er krav vi mener vi har fullført hittil. 


Alt i alt, er vi veldig fornøyd med denne arbeidsperioden. Vi har møtt på hindere, som blant annet utfordringene med situasjonen vi er i nå, men føler at vi har håndtert det greit. Men, det skal sies at det førte med seg en del endringer, som blant annet at det ble vanskeligere å ha møter. Dette har dermed gjort at vi har blitt flinkere på å fordele oppgaver og jobbe individuelt, og da bare ha "møter" når det er nødvendig. Vi har tatt imot tilbakemeldingen vi fikk fra forrige sprint, og føler vi har møtt disse kravene så godt som det kan. Vi har strammet opp ekstra på testfronten og skrevet litt manuelle tester samt også testet flere cases av eksisterende tester. Vi har derimot møtt på et lite hinder med Mockito. Vi har prøvd å få dette til å fungere ved å følge ulike tutorials, men virker som det fortsatt kræsjer med libgdx. Her skulle vi gjerne ønsket oss litt eksempler og tutorials på hvordan dette fungerer. 


