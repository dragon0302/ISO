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

<%
    Utente utente = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>ISO-16/Profilo Utente</title>

        <link rel="stylesheet" href="sfondo.css">
        <link rel="stylesheet" href="Home.css">

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
                            <li><a href="profile.jsp">Profilo</a></li>
                            <li><a href="settings.jsp">Impostazioni</a></li>
                            <li><a href="Carrello.jsp">Carrello</a></li>
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
                            <li><a href="profile.jsp">Profilo</a></li>
                            <li><a href="settings.jsp">Impostazioni</a></li>
                            <li><a href="Carrello.jsp">Carrello</a></li>
                            <li><a href="logout.jsp">Log-out</a></li>
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
          // AC/DC
          "AC/DC T-Shirt", "AC/DC CD", "AC/DC Hoodie", "AC/DC Vinyl", "AC/DC Poster", "AC/DC Cap", "AC/DC Mug",

          // Adele
          "Adele T-Shirt", "Adele CD", "Adele Hoodie", "Adele Vinyl", "Adele Poster", "Adele Cap", "Adele Mug",

          // Metallica
          "Metallica T-Shirt", "Metallica CD", "Metallica Hoodie", "Metallica Vinyl", "Metallica Poster", "Metallica Cap", "Metallica Mug",

          // Queen
          "Queen T-Shirt", "Queen CD", "Queen Hoodie", "Queen Vinyl", "Queen Poster", "Queen Cap", "Queen Mug",

          // Eminem
          "Eminem T-Shirt", "Eminem CD", "Eminem Hoodie", "Eminem Vinyl", "Eminem Poster", "Eminem Cap", "Eminem Mug",

          // Taylor Swift
          "Taylor Swift T-Shirt", "Taylor Swift CD", "Taylor Swift Hoodie", "Taylor Swift Vinyl", "Taylor Swift Poster", "Taylor Swift Cap", "Taylor Swift Mug",

          // Iron Maiden
          "Iron Maiden T-Shirt", "Iron Maiden CD", "Iron Maiden Hoodie", "Iron Maiden Vinyl", "Iron Maiden Poster", "Iron Maiden Cap", "Iron Maiden Mug",

          // Led Zeppelin
          "Led Zeppelin T-Shirt", "Led Zeppelin CD", "Led Zeppelin Hoodie", "Led Zeppelin Vinyl", "Led Zeppelin Poster", "Led Zeppelin Cap", "Led Zeppelin Mug"
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
