<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 28/04/2025
  Time: 08:03
  To change this template use File | Settings | File Templates.
--%>
<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page import="DataManagement.Utente" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Termini e condizioni</title>

    <!-- Link al file CSS esterno per lo styling della pagina-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sfondo.css">

</head>
    <body>

        <script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
        <script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

        <!-- TOP HEADER -->
        <header>
            <div class="top-header">
                <!-- Parte 1 - Logo a sinistra -->
                <div class="logo-container">
                    <a href="${pageContext.request.contextPath}/Catalogo">
                        <img src=" <%= request.getContextPath() + "/Immagini/isologo.png" %>" alt="Immagine Prodotto">
                    </a>
                </div>

                <!-- Parte 2 - Barra di ricerca centrata e filtri sotto -->
                <div class="center-section">
                    <!-- Barra di ricerca -->
                    <div class="search-container">
                        <input type="text" id="searchInput" placeholder="Cerca prodotti..." onkeyup="showSuggestions(this.value)">
                        <div class="suggestions" id="suggestionBox"></div>
                    </div>

                    <!-- Filtri sotto la barra di ricerca -->
                    <div class="filter-bar">
                        <div class="filter">
                            <a href="#">ROCK</a>
                            <div class="dropdown">
                                <a href="#">AC/DC</a>
                                <a href="#">Aerosmith</a>
                                <a href="#">Led Zeppelin</a>
                            </div>
                        </div>
                        <div class="filter">
                            <a href="#">POP</a>
                            <div class="dropdown">
                                <a href="#">Adele</a>
                                <a href="#">Beyoncé</a>
                                <a href="#">Madonna</a>
                            </div>
                        </div>
                        <div class="filter">
                            <a href="#">JAZZ</a>
                            <div class="dropdown">
                                <a href="#">Coltrane</a>
                                <a href="#">Davis</a>
                                <a href="#">Fitzgerald</a>
                            </div>
                        </div>
                        <div class="filter">
                            <a href="#">METAL</a>
                            <div class="dropdown">
                                <a href="#">Iron Maiden</a>
                                <a href="#">Metallica</a>
                                <a href="#">Slayer</a>
                            </div>
                        </div>
                        <div class="filter">
                            <a href="#">RAP</a>
                            <div class="dropdown">
                                <a href="#">Drake</a>
                                <a href="#">Eminem</a>
                                <a href="#">Kanye</a>
                            </div>
                        </div>
                        <div class="filter">
                            <a href="#">Abbigliamento</a>
                            <div class="dropdown">
                                <a href="#">Cappelli</a>
                                <a href="#">Felpe</a>
                                <a href="#">T-shirt</a>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Parte 3 - Bottoni di Sign-up e Log-in a destra -->
                <div class="right-section">
                    <a class="btn-link" href="Carrello">Carrello</a>
                    <%
                        Utente utente = (Utente) session.getAttribute("utente");

                        if (utente == null) { %>
                    <!-- Se l'utente non è loggato, mostra i bottoni di login e signup -->
                    <a class="btn login-btn" href="Log_in.jsp">Log In</a>
                    <a class="btn signup-btn" href="Sign-up.jsp">Sign Up</a>
                    <% } else { %>
                    <!-- Se l'utente è loggato, mostra il nome utente o il menu amministratore -->
                    <% if (utente.isAmministratore()) { %>
                    <!-- Amministratore: aggiungi i bottoni di gestione e il menu a tendina -->
                    <span class="username" onclick="toggleUserMenu()"><%= utente.getNomeutente()!= null ? utente.getNomeutente().toUpperCase() : "" %></span>
                    <div id="userMenu" class="user-menu">
                        <ul>
                            <li><a href="Profilo.jsp">Profilo</a></li>
                            <li><a href="Impostazioni.jsp">Impostazioni</a></li>

                            <form action="Logout" method="get">
                                <li><button>Log-out</button></li>
                            </form>
                        </ul>
                    </div>
                    <!-- Bottoni amministratore -->
                    <button class="btn-link" onclick="openPriceSurveyModal()">Indagine per numero venduti</button>
                    <button class="btn-link" onclick="openAddProductModal()">Aggiungi Prodotto</button>
                    <button class="btn-link" onclick="openAddFilterModal()">Aggiungi Filtro</button>
                    <button class="btn-link" onclick="openDateSurveyModal()">Indagine per Data</button>
                    <% } else { %>
                    <!-- Utente non amministratore: solo il nome utente e il menu a tendina -->
                    <span class="username" onclick="toggleUserMenu()"><%= utente.getNomeutente() != null ? utente.getNomeutente().toUpperCase() : "" %></span>
                    <div id="userMenu" class="user-menu">
                        <ul>
                            <li><a href="Profilo.jsp">Profilo</a></li>
                            <li><a href="Impostazioni.jsp">Impostazioni</a></li>

                            <form action="Logout" method="get">
                                <li><button>Log-out</button></li>
                            </form>
                        </ul>
                    </div>
                    <% } %>
                    <% } %>
                </div>
            </div>
        </header>

        <!-- MAIN CONTENT -->
        <main>
            <section class="Termini_e_condizioni">
                <h1>Termini e condizioni del sito</h1>

                <h3>Di seguoito vengono riportati i termini e condizioni relative alle interazioni del sito ed hai ccokie</h3>
                <p>Questa informativa sui cookie è stata redatta con l'intenzione di comunicare ai visitatori del nostro sito Web
                    informazioni sulle tecnologie utilizzate sul nostro sito Web e su come i nostri visitatori possono effettuare
                    scelte informate sulla gestione dei cookie.</p>

                <h4><b>Che cos'è un cookie?</b></h4>
                <p>Ogni volta che visiti il nostro sito Web, inseriamo i cookie sul tuo dispositivo per diversi motivi.
                    Un cookie è un piccolo file di testo che viene scaricato e memorizzato sul tuo computer, dispositivo
                    mobile o dispositivo analogo e contiene informazioni sulla tua navigazione sul sito Web. Possono, ad
                    esempio, essere utilizzati per tenere traccia delle pagine che visiti sul sito Web, per salvare le
                    informazioni che hai inserito o le tue preferenze memorizzate, come le impostazioni della lingua.</p>

                <h4><b>Perché li utilizziamo?</b></h4>
                <p>Utilizziamo i cookie per offrirti la completa funzionalità del sito Web, personalizzare l'esperienza
                    utente, eseguire analisi e migliorare i nostri servizi. I cookie vengono utilizzati anche per inviare
                    pubblicità personalizzata sui nostri siti Web, app e newsletter su Internet e tramite le piattaforme
                    dei social media, per ottenere informazioni, ad esempio, sul numero di persone che cliccano su un nostro
                    annuncio sui social media per visitare una pagina del nostro sito Web. Le informazioni vengono utilizzate
                    per misurare e ottimizzare la pubblicità sui social media. </p>

                <h4><b>Chi è responsabile dell'inserimento dei cookie sul nostro sito Web?</b></h4>
                <p>Tutti i cookie hanno un editore che comunica a chi appartiene il cookie. Alcuni cookie vengono inseriti nei
                    siti Web da noi, tali cookie sono detti "cookie di prima parte", altri vengono inseriti nel sito Web da un'altra
                    organizzazione, con la nostra autorizzazione. Tali cookie sono detti "cookie di terze parti". </p>

                <h4><b>Per quanto tempo vengono memorizzati i cookie?</b></h4>
                <p>I cookie possono essere memorizzati nel browser o nel dispositivo per un periodo di tempo variabile. I cookie
                    temporanei, detti cookie di sessione, vengono memorizzati nel dispositivo fino alla chiusura del browser.
                    I cookie permanenti hanno una data di scadenza e una volta trascorsa tale data, il cookie viene eliminato quando
                    si torna al sito Web che lo ha creato. </p>

                <h4><b>Quali tipi di cookie utilizziamo?</b></h4>
                <p>Utilizziamo quattro categorie di cookie, strettamente necessari, di prestazione, funzionali e di marketing.
                    Solo le ultime tre categorie elencate richiedono il consenso dell'utente. Per i cookie strettamente necessari,
                    non è necessario il consenso dell'utente perché questi cookie consentono una visualizzazione completa e continua
                    del contenuto del sito Web in modo da permettere l'accesso al sito Web e un'esperienza di navigazione digitale e online appropriata. </p>

                <h4><b>Come accettare o revocare il consenso ai cookie nelle "Impostazioni cookie"?</b></h4>
                <p>Puoi gestire il consenso sui cookie in "Impostazioni cookie" in fondo a questo sito Web. Puoi accettare tutte e
                    tre le categorie di cookie o solo una di esse, se preferisci. Accettando una categoria di cookie, accetti tutti
                    i cookie di questa categoria (vedere l'elenco dettagliato dei cookie di seguito).  Puoi modificare le tue preferenze
                    e rifiutare i cookie in qualsiasi momento in Impostazioni cookie. Di seguito sono riportate informazioni più dettagliate
                    sulle nostre categorie di cookie e un elenco di tutti i cookie utilizzati in tale categoria.</p>

                <h4><b>Domande?</b></h4>
                <p>In caso di domande sul trattamento dei dati personali, ti invitiamo a leggere la nostra Informativa sulla Privacy dove
                    troverai anche le nostre informazioni di contatto.</p>
            </section>
        </main>

        <!-- FOOTER -->
        <div class="footer-bar">
            <a href="About_Us.jsp" class="btn-link">About Us</a>
            <a href="Contattaci.jsp" class="btn-link">Contattaci</a>
            <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
            <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
        </div>

    </body>
</html>