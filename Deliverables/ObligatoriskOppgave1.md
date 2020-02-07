## Gruppenavn : Aerial-stingray

#### Kartlegging av kompetanse:

Alle på gruppen har vært gjennom de mest grunnleggende inf fagene som 100, 101 og 102, men kompetansenivået innad i disse fagene vil jo naturlig variere fra person til person. I grupper har vi to stykker som går 2.året på datateknologi, en som går 2. året på IKT, en som går 3. året på bioinformatikk og en siste som går 3. året på datasikkerhet. Alle har programmert tidligere i Java, men litt usikkerhet rundt kjennskapet med de ulike bibliotekene. Vi har heller ingen erfaring med liknende gruppeprosjekt tidligere, og innser at vi er nødt til å tilpasse oss underveis i oppgaven ettersom hva som fungerer for oss. 


#### Roller:
 
**Teamlead**: Tiril

Tiril passer til  teamlead for gruppen ettersom at hun ikke anser seg selv som veldig erfaren og selvstendig innen programmering, men ønsker heller ha andre fokusområder som organisering, planlegging av møter og fasilitere god kommunikasjon. 
 
**Planleggingsingeniør**: Dan

Dan er tildelt planleggingsingeniør - som vil ha et overordnet ansvar for planleggingen og strukturen av selve programmet. Arbeidsområdet vil være å ha et helhetlig bilde og oversikt over koden, og sørge for at det er en sammenheng og struktur til stede, noe som passer Dan ypperlig. 

**Kode auditør**: Lars

Lars har fått tittelen kode auditør, som vil ha hovedansvar for å “korrekturlese”/debugge koden, fjerne tydelige, klare feil og stiller til disposisjon hvis noen trenger hjelp. Denne rollen tildeles Lars ettersom han har en del erfaring med programmering. Han har også programmert/brukt sockets, som kan være nytting for evt. flerspiller-modus. 

**Test auditør**: Maren

Den neste rollen er test auditør, som er tildelt Maren. Her vil fokusområde være å gå over at alt fungerer som det skal, både tester og selve spillet. Alle har ansvar til å skrive egne tester, men maren skal gå over å se om testene tester det de skal.

**Kundekontakt**: Steffen.

Steffen blir gruppens kundekontakt. Skal ha ansvar for å ta imot krav og forespørsler fra gruppeleder og foreleser, og også være vårt ansikt utad til kunden hvis vi har noen spørsmål. 

 
 
Vi har valgt å ikke tildele noen et ansvarsområde for grafikk, ettersom at ingen har særlig erfaring, så dette er noe vi ønsker å finne ut av sammen som en gruppe. 
 
Videre har vi valgt å bruke github Projectboard til planlegging og organisering av arbeidet, ettersom at vi da får samlet alt på en plass som gir mer oversikt.
 
 
 
**Mål**: Ønske  å lage en enkel, grafikk basert versjon av RoboRally. Vi har to brukere, og målet er at vi kan bevege de forskjellige elementene rundt på brettet. En enkel versjon av RoboRally, som fungerer.
 
#### Høynivå krav:
* Trenger et spillbrett
* Trenger en spiller
* Visuell illustrasjon av hvor man befinner seg på brettet
* Kunne spille på to ulike enheter, men samtidig være på samme brett – Multiplayer
* Ta skade
* Plukke opp flagg
* En robot kan dø
* En spiller kan dø
* Avslutte spillet, enten ved at alle dør eller at noen vinner
* Kortstokk
    * Når det er tomt for kort – shuffle kortene
* Rekkefølge
* Vegger
* Events
    * Samlebånd, tannhjul, lasere
    * Dør når man havner utenfor brettet
* Justere antall kort basert på skade
* Dytte andre roboter
* Timeglass
* Back-up
* Gjennomføre en fase
* Velge kort
* Bekrefte kort
* Låse kort
* Flytte brikker

#### Prioritert liste for første sprint:
* Trenger et spillbrett
* Trenger en spiller
* Visulle illustrasjon av hvor man befinner seg på brettet


#### Prosjektmetodikk
Når det kommer til prosjektmetodikk tenker vi å ikke velge en bestemt, men heller plukke elementer som passer vår gruppe. Dette basert på våre erfaringer med liknende oppgaver, og dermed gi oss mer rom for muligheten til å utvikle oss som en gruppe med et godt samarbeid underveis, istedenfor å møtte følge en etablert prosedyre fra starten av. Med utgangspunkt i oppgavens utforming vil vi kunne ta i bruk sprinter fra Scrum. Dette basert på at det blir obligatoriske innleveringer på mellom 2-4 uker, hvor målet blir å bli ferdig med oppgaven innen fristen. Derfor blir det naturlig å ha sprint-planleggingsmøter for hver deloppgave, hvor vi kartlegger hva som skal gjøres og hvem som skal gjøre hva innen sprinten er over. Dette legges inn i vår Backlog. Dette vil ikke følges like strengt som i scrum, men heller følge scrumban ettersom vi vil holde muligheten oppe for å legge til gjøremål underveis som vi anser som viktig for denne sprinten.  Vi vil også benytte oss av det retrospektivet stadiet til scrum, hvor vi kan se tilbake og endre videre arbeidsmetode for å best forme vår fremgangsmåte. Hva kan gjøres bedre, hva har fungert bra og hva skal tas med videre. Vi kommer også til å gå vekk fra daglige sprintmøter, men vi tenker å ta med oss hovedpunktene fra disse møtene – nemlig hva er blitt gjort siden sist, hindre vi har møtt på og hva skal gjøres til neste gang. Dette vil gjennomgås på møter vi har 2-4 ganger i løpet av uken, med varighet på alt fra 30 minutter til 2 timer.

Vi har også muligheten oppe for å jobbe på egenhånd evt. Jobbe med utdelte arbeidsoppgaver, men man må kommentere hva man har gjort slik at resten av gruppen får med seg hva som er blitt gjort. Vi vil benytte oss av ulike kommunikasjonsplattformer mellom møter, hvor Slack blir benyttet for mer formelle henvendelser og facebook blir forbeholdt til mer uformelle meldinger. Vi vil etterhvert fordele oss på grupper på 2 og 3, og diskutere avgjørelser gruppen må ta sammen alle 5. Nå i begynnelsen tenker vi det er best å jobbe sammen – slik at alle får med seg hoveddelen av mekanismen og at alle vet hvordan den fungerer. Når det kommer til deling av dokumenter vil vi benytte oss av google docs, som senere vil bli konvertert til markdown, mens deling av kode og diagram vil foregå på github. 



#### Brukerhistorier
Brukerhistorier for brett:
* Som spiller trenger jeg et brett for å faktisk kunne spille spillet
* Som spiller trenger jeg et brett for å kunne se hvor jeg kan flytte roboten min

Brukerhistorie for brikke:
* Som spiller trenger jeg en brikke for å se hvilken robot som er meg og hvor jeg befinner meg 
* Som spiller trenger jeg en retning på brikken for å vite i hvilken retning roboten min går/peker
 
#### Retrospektiv

Vi føler kanskje det er litt tidlig å vurdere hvordan arbeidsmetoden vår fungerer, men enn så lenge har vi ikke støtt på noen problemer. Vi brukte mesteparten av tiden til å kartlegge og planlegge tiden fremover, og mot slutten gjorde vi kode-delen av oppgaven som vi endte opp med å gjøre i fellesskap. Det som fungerte veldig bra for oss i løpet av denne sprinten, var de felles kode “sessionene”, siden alle fikk delta, få med seg hva som ble gjort og forstå det. Ettersom dette fungerte veldig bra for oss, så kommer vi til å fortsette med det frem til større kode oppgaver - hvor vi eventuelt kan dele oss opp i mindre grupper for mer effektivt utbytte av tiden. Har også skrevet referat underveis, som ligger ute på wikisiden på github prosjektet vårt. 

