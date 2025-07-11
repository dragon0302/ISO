<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page import="DataManagement.Utente" %>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>Assistenza</title>

        <link rel="stylesheet" href="../sfondo.css">
        <link rel="stylesheet" href="../FILE_CSS/Assistenza.css">
    <body>

    <script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
    <script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

    <header>
        <div class="top-header">
            <!-- Parte 1 - Logo a sinistra -->
            <div class="logo-container">
                <a href="Catalogo">
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
            <div class="faq-container">
                <div class="faq-item">
                    <h3>Come posso effettuare un ordine?</h3>
                    <p>Per ordinare, cerca i tuoi prodotti preferiti, aggiungili al carrello e segui la procedura di checkout.</p>
                </div>

                <div class="faq-item">
                    <h3>Quali metodi di pagamento accettate?</h3>
                    <p>Accettiamo carte di credito/debito e PayPal.</p>
                </div>

                <div class="faq-item">
                    <h3>Quanto tempo impiega la consegna?</h3>
                    <p>Generalmente, le consegne avvengono entro 3-5 giorni lavorativi.</p>
                </div>

                <div class="faq-item">
                    <h3>Posso restituire un prodotto?</h3>
                    <p>Sì, hai 14 giorni per effettuare il reso. Visita la sezione "Profilo" per gestire i tuoi resi.</p>
                </div>

                <div class="faq-item">
                    <h3>Come posso contattare l’assistenza?</h3>
                    <p>Puoi usare il nostro modulo nella pagina Contattaci o inviarci un'email a <strong>support@iso16.it</strong>.</p>
                </div>

                <div class="contact-link">
                    <a href="Contattaci.jsp">Contatta il supporto</a>
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