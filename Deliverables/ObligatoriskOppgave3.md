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

_**Gjennomføre en fase.**_

Brukerhistorie:

Arbeidsoppgave:

Akseptansekriterie:

_**Bekrefte kort.**_

Brukerhistorie:

Arbeidsoppgave:

Akseptansekriterie:

_**Legge til programkort.**_

Brukerhistorie: Som spiller trenger jeg programkort for å kunne flytte spilleren min.

Arbeidsoppgave: Lage en ny package for kortstokken; cards. Lage et enum for alle korttypene vi har. Deretter en egen klasse for hvert programkort hvor vi implementerer en konstruktør, og get() metode for både type og prioritet. Så lage en klasse for hele kortstokken. Når designet på kortene er laget, implementeres dette i klassene.

Akseptansekriterie: Hvis spilleren velger programkort, skal roboten flytte seg etter kriteriene på disse.

_**Velge kort**_

Brukerhistorie: Som spiller trenger jeg å kunne velge kort, for å selv kunne styre roboten min.

Arbeidsoppgave:

Akseptansekriterie: Hvis spiller velger kort i en viss rekkefølge, skal den utføres i den rekkefølgen den runden.

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

Oppdatert mvp - liste: 
