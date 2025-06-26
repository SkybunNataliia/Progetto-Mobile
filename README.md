# App Meteo

Questo progetto è stato realizzato come parte del corso universitario di _Laboratorio di Programmazione di Sistemi Mobili_.

### Scopo del progetto

L'obiettivo del progetto è lo sviluppo di una **applicazione Android Meteo** che consenta all'utente di:

- Cercare una città per visualizzarne le previsioni meteo attuali.
- Salvare le città preferite.
- Consultare le previsioni di 5 giorni per ogni città.
- Rimuovere città dai preferiti.

L’obiettivo era realizzare un’app funzionale e intuitiva, adottando architetture moderne e garantendo una buona separazione tra logica di business, presentazione e dati.

### Struttura dei sorgenti

Il progetto è stato suddiviso in tre moduli principali per garantire una chiara separazione delle responsabilità:  

**Modulo domain**  
Contiene la logica di business e le astrazioni del dominio:  
_di/_  
AppModule.kt: definisce i use case e i repository da iniettare nei livelli superiori.

_models/_  
Weather.kt  
City.kt  
Forecast.kt

_usecases/_  
GetWeatherForCity.kt  
GetCurrentWeather.kt  
GetFavoriteCities.kt  
AddFavoriteCity.kt  
RemoveFavoriteCity.kt  

_repository/_  
WeatherRepository.kt: interfaccia per accedere a dati meteo, utilizzata nei use case.

**Modulo data**  
Contiene l’implementazione concreta della logica di accesso ai dati:  

_di/_  
AppModule.kt: definisce i provider di oggetti per l’iniezione delle dipendenze (Retrofit, Repository, SharedPreferences).

_local/_  
PreferencesManager.kt: gestisce il salvataggio e il recupero delle città preferite tramite SharedPreferences.

_remote/_  
WeatherApiService.kt: interfaccia Retrofit per definire le chiamate HTTP verso il servizio meteo.  
WeatherRemoteDataSource.kt: implementa le chiamate alla rete verso l’API.

_repository/_  
WeatherRepositoryImpl.kt: implementazione concreta di WeatherRepository.

**Modulo ui**  
Gestisce la presentazione e l’interazione con l’utente:  

MainActivity.kt: activity principale con configurazione della navigazione tra fragment.

_splash/_  
SplashActivity.kt: schermata iniziale dell’app.

_home/_  
HomeFragment.kt: visualizza le città preferite con le condizioni meteo attuali.  
HomeViewModel.kt: recupera i dati e li espone alla vista con StateFlow.

_search/_  
SearchFragment.kt: consente la ricerca di una nuova città e l’aggiunta ai preferiti.  
SearchViewModel.kt: gestisce la logica di ricerca e stato UI.

_forecast/_  
ForecastFragment.kt: mostra la previsione meteo dettagliata (ogni 3 ore per 5 giorni).  
ForecastViewModel.kt: carica la previsione tramite il use case.

### Punti di forza

Il progetto si distingue per alcune scelte architetturali e tecniche:  

- Uso di Kotlin Flow e StateFlow.

- Memorizzazione dei preferiti nelle SharedPreferences.

- ViewBinding: migliora la sicurezza del codice rispetto ai riferimenti XML.

- Navigation basata su FragmentTransaction.

- Uso di DiffUtil nei RecyclerView Adapter.

- Uso di componenti del Material Design.

- Uso di Toast per feedback al utente.

### Possibili migliorie
Nonostante il progetto sia funzionante e ben strutturato, ci sono diversi aspetti che potrebbero essere migliorati o estesi in futuro:

- Utilizzo di un database locale (es. Room): per salvare le città preferite in modo persistente.

- Navigation Component: per una gestione più dichiarativa e sicura della navigazione tra fragment.

- Modalità light e supporto multilingua: per migliorare l’accessibilità e l’esperienza utente.
 
- Notifiche personalizzate o widget meteo.

- Miglioramenti generali della UI rendendola più interattiva.

- Test Unitari e di UI.


