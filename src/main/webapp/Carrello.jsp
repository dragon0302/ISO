<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page import="DataManagement.Utente" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ricerca & Filtri</title>
    <link rel="stylesheet" href="sfondo.css">
    <link rel="stylesheet" href="Carrello.css">
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
                <a class="btn-link" href="Carrello.jsp">Carrello</a>
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
                        <li><a href="cart.jsp">Carrello</a></li>
                        <li><a href="logout.jsp">Log-out</a></li>
                    </ul>
                </div>
                <!-- Bottoni amministratore -->
                <button class="btn-link" onclick="openPriceSurveyModal()">Indagine per numero venduti</button>
                <button class="btn-link" onclick="openAddProductModal()">Aggiungi Prodotto</button>
                <button class="btn-link" onclick="openAddFilterModal()">Aggiungi Filtro</button>
                <button class="btn-link" onclick="openDateSurveyModal()">Indagine per Data</button>
                <button class="btn-link" onclick="openDeleteModal()">Elimina</button>
                <% } else { %>
                <!-- Utente non amministratore: solo il nome utente e il menu a tendina -->
                <span class="username" onclick="toggleUserMenu()"><%= utente.getNomeutente() != null ? utente.getNomeutente().toUpperCase() : "" %></span>
                <div id="userMenu" class="user-menu">
                    <ul>
                        <li><a href="profile.jsp">Profilo</a></li>
                        <li><a href="settings.jsp">Impostazioni</a></li>
                        <li><a href="cart.jsp">Carrello</a></li>
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
    <div class="box-container">
        <div class="box-prodotti">
            <%
            // Recupera i prodotti dal database
            ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) session.getAttribute("carrello");
            ArrayList<Integer> quantita = (ArrayList<Integer>) session.getAttribute("Quantità");
            float prezzotatale = (float) session.getAttribute("prezzotatale");

            //Visualizza la barra orizzontale per ogni prodotto -->
            if (prodotti != null){
                for (int i = 0; i < prodotti.size(); i++) {
        %>
        <div class="product-bar">
            <!-- Immagine -->
            <!--img src="<//%= prodotto.getImmagine() %>" alt="Immagine prodotto"-->

            <!-- Descrizione -->
            <div class="descrizione">
                <%= prodotti.get(i).getDescrizione() %>
            </div>

            <!-- Prezzo e quantità -->
            <div class="prezzo-quantita">
                <div class="prezzo">€ <%= prodotti.get(i).getPrezzo() %></div>
                <label for="numero">Scegli un numero:</label>
                <input type="number" id="numero" name="numero" min="0" max="100" step="1" value= <%= quantita.get(i) %>>
            </div>
        </div>
            <%  }
                %>

            <div>
                <div class="PT">Prezzo Totale</div>
                <div class="prezzo-totale"><%= prezzotatale%> </div>
            </div>
        <%}%>

            </div>


        <div class="box-acquista">
            <form action="NewOrder" method="post">
    <%--            <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">--%>
    <%--            <input type="hidden" name="SourcePage" value="Home">--%>
                <button type="submit" class="btn-aggiungi">
                    Vai al pagamento
                </button>
            </form>
        </div>
    </div>

<div class="footer-bar">
    <a href="About_Us.jsp" class="btn-link">About Us</a>
    <a href="contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

</body>
</html>





