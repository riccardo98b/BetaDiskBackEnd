
## 🛠️ BetaDiskBackEnd

**BackEndBetacom** è il backend di un'applicazione eCommerce dedicata alla vendita di prodotti musicali. 
Il progetto è stato sviluppato in Java con Spring Boot e segue l'architettura RESTful. Include funzionalità avanzate per la gestione dei prodotti, utenti e ordini, ed è pensato per offrire un'esperienza completa sia lato cliente che lato amministratore.

### 🚀 Funzionalità principali

- ✅ **CRUD completo** per la gestione dei prodotti
- 📊 **Dashboard per admin** con statistiche e gestione utenti/prodotti
- 📝 **Recensioni utenti** sui prodotti acquistati
- 💸 **Possibilità Acquisti con contrassegno**
- 🔍 **Filtri di ricerca ** per i prodotti
- ❌ **Cancellazione ordini** disponibile **fino a quando l’ordine non viene spedito**
- 💖 **Wishlist** per salvare i prodotti preferiti
- 🛒 **Carrello** integrato con supporto alla fase di checkout

### 🧪 Testing:

- ✅ **Copertura dei test JUnit**: circa **80%**

### 🛠️ Stack Tecnologico:

- **Linguaggio:** Java  
- **Framework:** Spring Boot  
- **Database:** MySQL  
- **Sicurezza:** Spring Security
- **Testing:** JUnit

---


## Struttura del Progetto
Il progetto è suddiviso nelle seguenti componenti principali:
- **Backend Java**: Gestisce le logiche di business, le operazioni CRUD e la comunicazione con il database.
- **Frontend Web**: Interfaccia utente costruita con HTML, CSS, Bootstrap e Angular.
- **Database**: Memorizzazione dei dati relativi.

## Prerequisiti
Prima di iniziare, assicurati di avere:
- **Java Development Kit (JDK) 17 o superiore**
- **Un browser web** (consigliato Chrome o Firefox)
- **Un IDE compatibile con Java** (ad esempio, IntelliJ IDEA o Eclipse)
  
## Installazione
<p>Segui questi passaggi per configurare l'ambiente: </p>
<ul>
 <li>1. **Clona il repository**:
   ```bash
   git clone [https://github.com/riccardo98b/ReportTracker.git](https://github.com/riccardo98b/BetaDiskBackEnd.git)
 </li>
 <li>Importa il progetto nel tuo IDE preferito.</li>
 <li>Compila ed esegui il backend Java per avviare il server.</li>
 <li>Accedi al frontend tramite il browser, inserendo l'URL fornito dal server (ad esempio, http://localhost:8080).</li>
</ul>
  
## Configurazione Database
<p>Assicurati che il database sia correttamente configurato:</p>
<ul>
<li>Imposta il database utilizzando il file di configurazione application.properties.</li>
<li>Avvia le tabelle iniziali tramite gli script SQL generati tramite il file define.sql generato dopo l'esecuzione del progetto /.</li>
</ul>

🔗 [Repository GitHub](https://github.com/riccardo98b/BetaDiskBackEnd)
