<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DataManagement.Utente" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link rel="stylesheet" href="sfondo.css">
    <link rel="stylesheet" href="Log_in.css">
</head>
<body>

<script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
<script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

<header>
    <div class="top-header">
        <!-- Parte 1 - Logo a sinistra -->
        <div class="logo-container">
            <a href="Home.jsp">
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
            <a class="btn" href="Carrello.jsp">Carrello</a>
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
                    <li><a href="Carrello.jsp">Carrello</a></li>
                    <form action="Logout" method="get">
                        <li><button>Log-out</button></li>
                    </form>
                </ul>
            </div>
            <!-- Bottoni amministratore -->
            <button class="btn" onclick="openPriceSurveyModal()">Indagine per nnumero venduti</button>
            <button class="btn" onclick="openAddProductModal()">Aggiungi Prodotto</button>
            <button class="btn" onclick="openAddFilterModal()">Aggiungi Filtro</button>
            <button class="btn" onclick="openDateSurveyModal()">Indagine per Data</button>
            <button class="btn" onclick="openDeleteModal()">Elimina</button>
            <% } else { %>
            <!-- Utente non amministratore: solo il nome utente e il menu a tendina -->
            <span class="username" onclick="toggleUserMenu()"><%= utente.getNomeutente() != null ? utente.getNomeutente().toUpperCase() : "" %></span>
            <div id="userMenu" class="user-menu">
                <ul>
                    <li><a href="Profilo.jsp">Profilo</a></li>
                    <li><a href="Impostazioni.jsp">Impostazioni</a></li>
                    <li><a href="Carrello.jsp">Carrello</a></li>
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
    <div class="page-container">
        <!-- img src="left-image.png" alt="Immagine Sinistra" class="side-image" -->

        <div class="login-box">
            <h2>Accedi</h2>
            <form action="Login" method="POST">
                <div class="form-group">
                    <label for="username">Nome Utente</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <input type="submit" value="Accedi">
                </div>
                <%
                    String Errore_USER_PASS = (String) request.getAttribute("Errore_USER_PASS");
                    if (Errore_USER_PASS != null) {
                %>
                <div class="field-error"><%= Errore_USER_PASS %></div>
                <% } %>
            </form>
        </div>

        <!-- img src="right-image.png" alt="Immagine Destra" class="side-image" -->
    </div>
</main>

<!-- Footer con stile di barra inferiore -->
<div class="footer-bar">
    <a href="About_Us.jsp" class="btn-link">About Us</a>
    <a href="Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

</body>
</html>




