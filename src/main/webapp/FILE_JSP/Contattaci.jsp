<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 23/04/2025
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page import="DataManagement.Utente" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Contattaci</title>

    <!-- Link al file CSS esterno per lo styling della pagina-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
    <!-- Link al file CSS esterno per lo styling dell'elenco-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Contattaci.css">

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
                <!-- Immagine del logo -->
                <img src=" <%= request.getContextPath() + "/Immagini/isologo.png" %>" alt="Immagine Prodotto">
            </a>
        </div>

        <!-- Parte 2 - Barra di ricerca centrata e filtri sotto -->
        <div class="center-section">
            <div class="search-container">
                <input type="text" id="searchInput" placeholder="Cerca prodotti..." onkeyup="showSuggestions(this.value)">
                <div class="suggestions" id="suggestionBox"></div>
            </div>

            <div class="filter-bar">
                <!-- Aggiungi qui i filtri per generi musicali, se necessari -->
            </div>
        </div>

        <!-- Parte 3 - Bottoni di Sign-up e Log-in a destra -->
        <div class="right-section">
            <a class="btn" href="Carrello.jsp">Carrello</a>
            <!-- Controllo se l'utente è loggato per visualizzare i bottoni -->
            <%
                Utente utente = (Utente) session.getAttribute("utente");
                if (utente == null) {
            %>
            <a class="btn login-btn" href="Log_in.jsp">Log In</a>
            <a class="btn signup-btn" href="Sign-up.jsp">Sign Up</a>
            <% } else { %>
            <span class="username"><%= utente.getNomeutente() != null ? utente.getNomeutente().toUpperCase() : "" %></span>
            <% } %>
        </div>
    </div>
</header>

<!-- MAIN CONTENT -->
<main>
    <section class="contact-info">
        <!-- Titolo della sezione di contatto -->
        <h1>Contattaci</h1>

        <!-- Descrizione generale per la sezione di contatto -->
        <p>For any questions, problems or requests, we are at your disposal. Contact us using one of the following telephone numbers:</p>
        <p>Per qualsiasi domanda, problema o richiesta, siamo a vostra disposizione. Contattateci utilizzando uno dei seguenti numeri di telefono:</p>

        <!-- Elenco delle referenze telefoniche -->
        <ul>
            <li><strong>Telefono referente Italiano:</strong> +39 XXX XXX XXXX</li>
            <li><strong>English contact telephone:</strong> +44 XXX XXX XXXX</li>
            <li><strong>Téléphone de contact français:</strong> +33 XXX XXX XXXX</li>
            <li><strong>Deutsches Kontakttelefon:</strong> +49 XXX XXX XXX</li>
        </ul>
    </section>
</main>

<!-- FOOTER -->
<div class="footer-bar">
    <a href="About_Us.jsp" class="btn-link">About Us</a>
    <a href="Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

<!-- Script per la gestione delle raccomandazioni di ricerca -->


</body>
</html>
