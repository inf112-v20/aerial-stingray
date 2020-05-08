## Obligatorisk oppgave 4 av Aerial-stingray

### Retrospektiv

#### Roller
Tidligere har vi poengtert at vi synes det er vanskelig å ta i bruk rollene, og vi ville derfor heller prøve å rullere litt. Men, slik som ting har blitt nå i forhold til arbeidsmetode og kommunikasjonsmidler i coronatidene, så fant vi det nesten enda vanskeligere å ta i bruk roller. Alle stiller opp og gjør sin del av jobben, og når det kommer ekstra oppgaver som tydelig er tildelt en av rollene har det føltes naturlig og tildele disse oppgavene til de originale rollene. Slik vi har jobbet har fungert veldig bra, og vi har ikke hatt noe mangler i forhold til hvordan rollene har fungert - men vi kan innrømme at vi ikke helt har forstått bruken av roller i team med mindre det har vært høyst nødvendig. Konklusjonen er derfor at det har vært en svak tilstedeværelse av rolle-bruk, men at de kunne vært tydeligere. 

#### Erfaringer
Som sagt under presentasjonen har hele denne perioden vært en læringsprosess. Vi tar spesielt med oss videre viktigheten av godt samarbeid, og da spesielt god kommunikasjon. Videre har vi lært mer om prosjektmetodikk generelt, pluss det og ta det i bruk. Vi tar også med oss videre erfaringen vi har fått av å jobbe med sprint. Vil også legge til praktiske erfaringer som å ta i bruk project board og Github. 

#### Valgene vi har gjort og nedstenging av Universitetet
Vi føler mange av valgene vi har tatt er gode, og står fortsatt ved det siden start. Derimot er det alltid noe å pirke på og lære av, så vi kan vel hvertfall trekke frem vår nyligste “smell”. Dette henger også sammen med påvirkningen av korona - og det er da hvordan vi gikk frem for å arbeide videre med oppgaven etter nedstenging. Først og fremst endret vi arbeidsmetode ganske drastisk og gikk fra å møtes 3-4 ganger i uken hvor vi sammen løste issues, til å ha 1-2 møter over discord hvor vi fordelte oppgaver individuelt. Tanken var god, for vi ønsket nemlig blant annet å fordele mer commits og bli flinkere til å jobbe individuelt med oppgaver, men det førte også til en del rot i koden. Her tar vi kritikk på at vi kunne løst dette bedre, ved blant annet å planlegge felles “code sessions” over discord. Det er vel heller ikke til å kimse av at motivasjonen til å jobbe falt litt i disse perioder. Ikke like enkelt å gjøre skolearbeid på hjemmekontor med mange distraksjoner. 


#### Kommunikasjon
Når det kommer til kommunikasjonen har vi vel egentlig vært klar på dette fra begynnelsen av, nemlig at den fungerer veldig godt. Som alt annet har jo det også forbedringspotensiale, og det kan godt merkes at vi har blitt mer og mer komfortable med hverandre. Nå kan vi til og med ta oss selv i å ha dagligdagse samtaleemner, fremfor hele tiden snakke om oppgaven. Dette påvirker så klart effektiviteten av møtene, men samtidig vektlegger vi gruppedynamikken mer. Det kan også trekkes frem at kommunikasjonen ble litt påvirket av at vi har hatt mindre møter - dette fordi vi fant det lettere å møtes når alle var på skolen. Likevel er vi veldig fornøyd med gruppens evne til å kommunisere.

#### Oppsummert
Det er ikke noe som spesielt stikker seg ut som noe vi ville gjort annerledes, annet enn ikke fokusert så mye på multiplayer ettersom dette ble mye bortkastet tid siden vi ikke rakk å komme i mål. Godt oppsummert av hele perioden vil vi si at dette har gått veldig bra.


#### Skjermdump av project board etter første sprintmøte:

[![Skjermbilde-2020-04-23-kl-12-50-43.png](https://i.postimg.cc/pVq28JGF/Skjermbilde-2020-04-23-kl-12-50-43.png)](https://postimg.cc/PNvsnYkX)

Project boarden består nå av ulike bugs som vi må fikse opp i, og noen av denne sprintens mvp issues. Grunnen til at ikke alle er på listen, er at vi har kontinuerlig jobbet med oppgaven, og derfor closed issues før vi har begynt på siste skriftlige oppgave. 

### Krav

Nå gjensto bare de siste punktene på mvp listen vår, og vi setter inn støtet for å klare å fullføre disse. Dessverre var vi nødt til å se vekk fra multiplayer ettersom dette ble for tidkrevende til at vi ikke rakk å bli ferdig med det. Bortsett fra det kom vi i mål på de siste kravene. Kravene som gjensto:


* Prioritering på kortene
* Man må kunne skyte andre spillere innen rekkevidde med laser som peker rett frem
* Skademekanisme: Justere antall kort basert på skade
* Dytte andre roboter
* Gjennomføre en fase


#### Brukerhistorier, arbeidsoppgaver og akseptansekriterier:
_Disse vil også befinne seg under issues i projectboardet vårt._

_**Prioritering på kortene.**_

Brukerhistorie: Som spiller trenger jeg at kortene mine viser Priority points for at jeg kan se hvor "sterke" mine kort er.

Arbeidsoppgave: Vi må oppdatere grafikken til programkortene. Dette kan gjørest med å tegne en Label på toppen av kortet i Roborally.

Akseptansekriterie: Hvis priority points som er på programkortene samsvarer med getpriorites().


_**Man må kunne skyte andre spillere innen rekkevidde med laser som peker rett frem.**_

Brukerhistorie: Som spiller trenger jeg at lasere fungerer som de skal for å kunne planlegge trekkene mine.

Arbeidsoppgave: 

Akseptansekriterie: Hvis en brikke står i rekkevidden til en laser vil brikken ta en skade.


_**Skademekanisme: Justere antall kort basert på skade.**_

Brukerhistorie: Som spiller trenger jeg at kortene justeres basert på skade, for å gjøre spillet mer spennende.

Arbeidsoppgave: 

Akseptansekriterie: Hvis spilleren har damage points, skal kortene justeres heretter.


_**Dytte andre roboter**_

Brukerhistorie: Som spiller trenger jeg å kunne dytte andre roboter, for å gjøre spille mer spennende.

Arbeidsoppgave: 

Akseptansekriterie: Hvis en robot står i veien for en spilleren, vil spilleren kunne dytte roboten. 


_**Gjennomføre en fase**_

Brukerhistorie: Som spiller trenger jeg at jeg kan fullføre en fase, slik at jeg kan gå videre til neste. 

Arbeidsoppgave:

Akseptansekriterie:


#### Oppdatert mvp - liste: 

* **Trenger et spillbrett**
* **Trenger en spiller**
* **Flytte roboten**
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
* **Prioritering på kortene**
* **Rekkefølge på eventene**
* **Man må kunne skyte andre spillere innen rekkevidde med laser som peker rett frem**
* **Skademekanisme: Justere antall kort basert på skade**
* **Dytte andre roboter**
* **Gjennomføre en fase**
* Multiplayer vennlig

Mvp listen er så og si lik tidligere mvp liste, bortsett fra at vi måtte flytte punktet "Flytte roboten", ettersom dette er et punkt som ble gjort tidlig og oppgaven og har blir feilplassert sist runde. De uthevede punktene er krav vi mener vi har fullført hittil. 


Sluttvis kan vi vel bare si som sagt tidligere, og at vi alt i alt er fornøyd med hvordan oppgaven ble. Vi har møtt andre type utfordringer denne perioden som ikke var forutsett av noen, og selv om vi sikkert kunne håndtert det bedre, gjorde vi det beste vi kunne der og da. Dette har vært utrolig lærerikt og gitt en liten smakebit på hvordan jobbtilværelsen kan være for systemutviklere. Igjen, har vi tatt imot tilbakemeldingen vi fikk fra forrige sprint, og prøvd å møte disse kravene så godt vi klarer. For denne perioden har det kanskje ikke vært så mange krav på mvp listen som har vært hovedfokus, men rett og slett fikse opp i den koden vi har og prøve å gi best mulig kvalitet av spillet vi hittil har utviklet. 

