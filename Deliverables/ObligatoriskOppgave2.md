## Obligatorisk oppgave 2 av Aerial-stingray

#### Prosjekt og Prosjektstruktur
Vi føler vi ikke har kommet helt i gang med å ta i bruk rollene enda, derfor blir det litt vanskelig å se kritisk på dette for øyeblikket. Hittil har vi jobbet mest i team, så rollene flyter litt over i hverandre, men bruker de der det er rom for det. Vi ser heller ikke noe behov for å endre på dette enda, og beholder det slik som det er hvertfall frem til neste innlevering. Gruppedynamikken fungerer bra, og vi kommuniserer også veldig godt. Det er alltid rom for kommentarer og spørsmål, og alle blir hørt. Det har vært en ulik fordeling i commits, men som vi tidligere også har påpekt er dette fordi vi ofte jobber sammen på en pc som vi kobler opp til en storskjerm. I mellomtiden har vi forbedringspotensiale når det kommer til hyppigheten av møter - og da spesielt det å møtes mellom release og ny oppgave. Vi kan også bli flinkere på å fordele oppgaver til å jobbe med individuelt. 

Gruppen kommuniserer for øyeblikket mest gjennom messenger. Her planlegger vi møter, hva som må gjøres og eventuelt deler relevant informasjon. Vi bruker Slack bare når det er nødvendig, men vi kan bli litt flinkere til å kommunisere her og. Møtene våre varierer veldig i gjennomføring. Vi gjør alt fra å diskutere fremgangsmåte, se på ulike tutorials, code session og jobbe med oppgaven indivudelt. For øyeblikket er vi fornøyde med gjennomførelsen, og vi jobber godt sammen som et team. 

##### Forbedringspunkter til neste gang:
* Bli flinkere til å møtes 2-3 ganger i uken
* Fordele oppgaver individuelt

#### Krav
Vi har oppdatert ferdige brukerhistorier, akseptansekriterier og arbeidsoppgaver for 8 issues:
* Lage event-system
* Legge til hull 
* Backup
* Implementere når spilleren vinner spillet.
* Legge til normal-samlebånd
* Legge til express-samlebånd
* Legge til tannhjul
* Legge til vegger

Denne listen har blitt litt finjustert underveis i oppgaven. Vi startet med 12 issues, og følte senere vi hadde tatt oss litt vann over hodet og reduserte listen til 4 issues. Når vi følte vi hadde kontroll på oppgavene så lå i In progress listen vår, addet vi nye elementer til ToDo listen og endte opp med 8 issues totalt. Vi innser videre at vi kan bli flinkere på planlegging av sprinten. Vil også trekke frem at vi endret issuesene våre til norsk. Dette på bakgrunn av tilbakemelding angående akseptansekriterier og arbeidsoppgaver. Når vi skrev disse sammen med brukerhistoriene, fant vi ut at det var mer naturlig for oss å ha det på norsk, og bestemte oss for at vi konsekvent skulle bruke samme språk i Project Boardet vårt. 

Project boardet vårt underveis i oppgaven:








I forrige sprint implementerte vi brettet og visuelle illustrasjoner av hvor man befinner seg på brettet. Dette ønsker vi å grafisk forbedre for denne sprinten, slik at vi tar utgangspunkt i et ferdig utviklet brett. Dette gjør vi med å implementere kartet først slik at vi kan gi alle event tilene et eget object hvor eventets type blir gitt. Dette gjør det enkelt for oss å hente de ulike type event tilene med map.getLayers().get(“Objects”).getObjects(). Her vil vi implementere en sjekk på hvilken event tile som spilleren står på og tilstøtende tiles. Brikken vil utføre events etter hvilken type event tile spilleren står på. 

Et av hovedfokusene våre for denne perioden ble events - samlebånd, tannhjul etc. Vi tok utgangspunkt i denne fremgangsmåten siden vi vil ha ferdig metodene til eventene, før vi går videre med programkort, spilleren og dens ulike funksjoner. Vi føler dette gir oss en bedre oversikt, og vil gjøre det lettere å dele opp oppgaver til neste gang. 




MVP - liste:
* Trenger et spillbrett
* Trenger en spiller
* Visuelle illustrasjon av hvor man befinner seg på brettet
* Ta skade
* Plukke opp flagg
* En robot kan dø
* En spiller kan dø
* Avslutte spillet, enten ved at alle dør eller at noen vinner
* Kortstokk
* Prioritering på kortene
* Når det er tomt for kort – shuffle kortene
* Rekkefølge på eventene
* Vegger
* Events
* Samlebånd, tannhjul, lasere
* Dør når man havner utenfor brettet
* Justere antall kort basert på skade
* Dytte andre roboter
* Back-up
* Gjennomføre en fase
* Velge kort
* Bekrefte kort
* Låse kort
* Flytte roboten

Nice-to-have:
* Timeglass
* Animasjon på trekk & events
* Multiplayer
* Design på de forskjellige kortene
* Meny som spilleren kan bestemme om å trykke på:
  - “Multiplayer”
  - “Singleplayer”
  - “Help”
  - “Exit”
* Bakgrunnsmusikk

##### Retrospektiv

Denne sprinten har samarbeidet som alltids fungert godt, men vi har møtt noen hindringer på veien. Det ble mye frem og tilbake på project boardet vårt, hvor vi ikke helt klarte å planlegge hva vi fikk gjort for denne sprinten. Selv om prosjektmetodikken vår tillater å endre på dette underveis, er dette noe vi ønsker å unngå. Fleksibilitet er verdsatt, men skal ikke gå på bekostning av struktur. Når det er sagt klarte vi likevel å dra det i havn, og har heller lært av dette til neste gang. Vi har også tatt til oss tilbakemeldingene vi fikk fra forrige sprint, og har finjustere dette. Avsluttende føler vi at denne sprinten fungerte veldig godt til tross for små hindringer. Vi er åpne for endringer, og snakker åpent om eventuelle problemer. Vil også trekke frem at det alltid er en god tone på møtene våre. 
