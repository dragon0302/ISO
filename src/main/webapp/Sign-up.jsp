<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DataManagement.Utente" %>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sig-up</title>
        <!-- Link al file CSS esterno per lo styling della pagina-->
        <link rel="stylesheet" href="sfondo.css">
        <link rel="stylesheet" href="Sign-up.css">
</head>
    <body>
        <header>
            <div class="top-header">
                <!-- Parte 1 - Logo a sinistra -->
                <div class="logo-container">
                    <a href="Home.jsp">
                        <!--img src="logo.png" alt="Logo"-->
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
        <!-- JavaScript per suggerimenti live -->
        <script>
            const products = [
                "AC/DC T-Shirt", "Adele CD", "Metallica Hoodie",
                "Queen Vinyl", "Eminem Cap", "Jazz Mug",
                "Taylor Swift Poster", "Iron Maiden Patch", "Led Zeppelin T-Shirt"
            ];

            function showSuggestions(value) {
                const suggestionBox = document.getElementById('suggestionBox');
                suggestionBox.innerHTML = '';
                if (value.length === 0) return;

                const filtered = products.filter(product =>
                    product.toLowerCase().includes(value.toLowerCase())
                );

                filtered.forEach(product => {
                    const div = document.createElement('div');
                div.textContent = product;
                div.onclick = () => {
                    document.getElementById('searchInput').value = product;
                    suggestionBox.innerHTML = '';
                };
                suggestionBox.appendChild(div);
            });
        }
    </script>
    <script>
        function toggleUserMenu() {
            document.getElementById("userMenu").classList.toggle("show");
        }

        function closeModal(modalId) {
            document.getElementById(modalId).style.display = "none";
        }

        function openAddProductModal() {
            document.getElementById("addProductModal").style.display = "block";
        }

        function openPriceSurveyModal() {
            document.getElementById("priceSurveyModal").style.display = "block";
        }

        function openAddFilterModal() {
            document.getElementById("addFilterModal").style.display = "block";
        }

        function openDateSurveyModal() {
            document.getElementById("dateSurveyModal").style.display = "block";
        }

        function openDeleteModal() {
            document.getElementById("deleteModal").style.display = "block";
        }
    </script>

    <main>
        <div class="content-wrapper">
            <!-- Immagini laterali fisse -->
            <!-- img src="left-image.png" class="side-image left" alt="Immagine sinistra" -->
            <!-- img src="right-image.png" class="side-image right" alt="Immagine destra" -->

            <!-- Box centrale con form -->
            <div class="form-container">
                <h2>Registrazione Utente</h2>
                <form action="Sign-up" method="POST">
                    <input type="hidden" name="action" value="insert">

                    <div class="form-group">
                        <label for="CodiceFiscale">Codice Fiscale *</label>
                        <input type="text" name="CodiceFiscale" id="CodiceFiscale" required>
                    </div>

                    <div class="form-group">
                        <label for="NomeUtente">Nome Utente *</label>
                        <input type="text" name="NomeUtente" id="NomeUtente" required>
                    </div>

                    <div class="form-group">
                        <label for="Password">Password *</label>
                        <input type="password" name="Password" id="Password" required>
                    </div>

                    <div class="form-group">
                        <label for="Nome">Nome *</label>
                        <input type="text" name="Nome" id="Nome" required>
                    </div>

                    <div class="form-group">
                        <label for="Cognome">Cognome *</label>
                        <input type="text" name="Cognome" id="Cognome" required>
                    </div>

                    <div class="form-group">
                        <label>Sesso *</label>
                        <label>M<input type="radio" name="sesso" value="M" required></label>
                        <label>F<input type="radio" name="sesso" value="F" required></label>
                    </div>

                    <div class="form-group">
                        <label for="DataNascita">Data di Nascita *</label>
                        <input type="date" name="DataNascita" id="DataNascita" required>
                    </div>

                    <div class="form-group">
                        <label for="E-mail">E-mail *</label>
                        <input type="email" name="E-mail" id="E-mail" required>
                    </div>

                    <div class="form-group full-width">
                        <input type="submit" value="Registrati">
                    </div>
                </form>
            </div>
        </div>
    </main>

    </body>
</html>
