<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 28/04/2025
  Time: 08:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DataManagement.Utente" %>
<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Termini e condizioni</title>

    <!-- Link al file CSS esterno per lo styling della pagina-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">

</head>
    <body>

        <script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
        <script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

        <script src="${pageContext.request.contextPath}/Javascript/CatalogFilter.js"></script>
        <script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
        <script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

        <header>
            <div class="top-header">
                <div>
                    <%
                        if(utente == null){
                            request.setAttribute("isAmministratore", null);
                        }else{
                            request.setAttribute("isAmministratore", utente.isAmministratore());
                        }
                    %>
                    <jsp:include page="/prova_nuova_home/header.jsp" />
                </div>
            </div> <!-- fine top-header -->
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

            </section>
        </main>

        <!-- FOOTER -->
        <div class="footer-bar">
            <a href="${pageContext.request.contextPath}/FILE_JSP/About_Us.jsp" class="btn-link">About Us</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Contattaci.jsp" class="btn-link">Contattaci</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Assistenza.jsp" class="btn-link">Assistenza</a>
        </div>
    </body>
</html>