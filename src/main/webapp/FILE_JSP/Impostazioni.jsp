<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 02/04/2025
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 02/04/2025
  Time: 11:28
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
    <title>Impostazioni</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/sfondo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Impostazioni.css">
<body>

<script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
<script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

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


<main>

    <!-- BOX PRINCIPALE -->
    <div class="settings-box">

        <!-- SEZIONE 1: IMPOSTAZIONI ACCOUNT -->
        <div class="settings-section">
            <h2>Impostazioni Account</h2>

            <!-- Opzione: modifica dati -->
            <div class="settings-option">
                <label>Modifica dati account</label>
                <a href="#">Modifica</a>
            </div>

            <!-- Opzione: indirizzo di fatturazione -->
            <div class="settings-option">
                <label>Indirizzo di fatturazione</label>
                <a href="#">Gestisci</a>
            </div>
        </div>

        <!-- SEZIONE 2: IMPOSTAZIONI ACQUISTO -->
        <div class="settings-section">
            <h2>Impostazioni Acquisto</h2>

            <!-- Metodo di pagamento preferito -->
            <div class="settings-option">
                <label>Metodo di pagamento preferito</label>
                <a href="#">Imposta</a>
            </div>

            <!-- Storico ordini -->
            <div class="settings-option">
                <label>Storico ordini</label>
                <a href="#">Visualizza</a>
            </div>

            <!-- Fatturazione elettronica -->
            <div class="settings-option">
                <label>Fatturazione elettronica / Codice univoco / PEC</label>
                <a href="#">Gestisci</a>
            </div>
        </div>

        <!-- SEZIONE 3: NOTIFICHE -->
        <div class="settings-section">
            <h2>Notifiche</h2>

            <!-- Notifica: stato ordini -->
            <div class="settings-option">
                <label>Stato ordini e spedizioni</label>
                <label class="toggle-switch">
                    <input type="checkbox" checked> <!-- checked = attivo -->
                    <span class="slider"></span>
                </label>
            </div>

            <!-- Notifica: promozioni -->
            <div class="settings-option">
                <label>Avvisi di promozioni o offerte</label>
                <label class="toggle-switch">
                    <input type="checkbox"> <!-- non attivo di default -->
                    <span class="slider"></span>
                </label>
            </div>
        </div>

        <!-- SEZIONE 4: PRIVACY E SICUREZZA -->
        <div class="settings-section">
            <h2>Privacy e Sicurezza</h2>

            <!-- Cookie -->
            <div class="settings-option">
                <label>Gestione consensi cookies</label>
                <a href="#">Modifica</a>
            </div>

            <!-- Scarica dati -->
            <div class="settings-option">
                <label>Scarica i tuoi dati</label>
                <button class="download-btn">Scarica</button>
            </div>

            <!-- Elimina account -->
            <div class="settings-option">
                <label>Elimina account</label>
                <button class="delete-btn">Elimina</button>
            </div>
        </div>
    </div>

</main>

<div class="footer-bar">
    <a href="About_Us.jsp" class="btn-link">About Us</a>
    <a href="Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

</body>
</html>
