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
        <title>Profilo Utente</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/sfondo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Profilo.css">

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

            <div class="profile-container">
                <!-- Immagine profilo -->
                <div class="profile-image-section">
                    <!--img src="img/default-profile.png" alt="Immagine Profilo"-->
                </div>

                <!-- Informazioni -->
                <div class="profile-info-section">
                    <form action="AggiornaProfilo" method="post" class="profile-form">
                        <!-- Email e Nome Utente -->
                        <div class="form-row">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" value="<%= utente.getEmail() %>" required>
                            </div>

                            <div class="form-group">
                                <label for="username">Nome Utente</label>
                                <input type="text" name="username" id="username" value="<%= utente.getNomeutente() %>" required>
                            </div>
                        </div>

                        <!-- Password -->
                        <div class="form-group">
                            <label for="password">Password (lascia vuoto per non cambiare)</label>
                            <input type="password" name="password" id="password" placeholder="Nuova password">
                        </div>

                        <button type="submit" class="btn btn-save">Salva Modifiche</button>
                    </form>
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
