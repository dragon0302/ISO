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
    <title>ISO-16</title>

    <link rel="stylesheet" href="sfondo.css">
    <link rel="stylesheet" href="Home.css">
<body>
<script src="${pageContext.request.contextPath}/Javascript/CatalogFilter.js"></script>
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

            <form id="filterForm" action="Catalogo" method="post">

                <input type="hidden" id="action" name="action" value="">
                <input type="hidden" id="value" name="value" value="">

            </form>

            <!-- Filtri sotto la barra di ricerca -->
            <div class="filter-bar">
                <div class="filter">
                    <a href="#" onclick="submitFilter('ROCK')">ROCK</a>
                    <div class="dropdown">
                        <a href="#" onclick="submitFilter('AC/DC')">AC/DC</a>
                        <a href="#" onclick="submitFilter('Aerosmith')">Aerosmith</a>
                        <a href="#" onclick="submitFilter('Led Zeppelin')">Led Zeppelin</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#" onclick="submitFilter('POP')">POP</a>
                    <div class="dropdown">
                        <a href="#" onclick="submitFilter('Adele')">Adele</a>
                        <a href="#" onclick="submitFilter('Beyoncé')">Beyoncé</a>
                        <a href="#" onclick="submitFilter('Madonna')">Madonna</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#" onclick="submitFilter('JAZZ')">JAZZ</a>
                    <div class="dropdown">
                        <a href="#" onclick="submitFilter('Coltrane')">Coltrane</a>
                        <a href="#" onclick="submitFilter('Davis')">Davis</a>
                        <a href="#" onclick="submitFilter('Fitzgerald')">Fitzgerald</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#" onclick="submitFilter('METAL')">METAL</a>
                    <div class="dropdown">
                        <a href="#" onclick="submitFilter('Iron Maiden')">Iron Maiden</a>
                        <a href="#" onclick="submitFilter('Metallica')">Metallica</a>
                        <a href="#" onclick="submitFilter('Slayer')">Slayer</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#" onclick="submitFilter('RAP')">RAP</a>
                    <div class="dropdown">
                        <a href="#" onclick="submitFilter('Drake')">Drake</a>
                        <a href="#" onclick="submitFilter('Eminem')">Eminem</a>
                        <a href="#" onclick="submitFilter('Kanye')">Kanye</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#" onclick="submitFilter('Abbigliamento')">Abbigliamento</a>
                    <div class="dropdown">
                        <a href="#" onclick="submitFilter('Cappelli')">Cappelli</a>
                        <a href="#" onclick="submitFilter('Felpe')">Felpe</a>
                        <a href="#" onclick="submitFilter('T-shirt')">T-shirt</a>
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

            <%-- questo è il pulsante per creare un nuovo filtro--%>
            <button class="btn-link" onclick="openAddFilterModal()">Aggiungi Filtro</button>

            <%-- form per il nuovo filtro--%>
            <div id="addFilterModal" class="modal" style="display:none;">
                <div class="modal-content">
                    <span class="close" onclick="closeAddFilterModal()">&times;</span>
                    <h2>Aggiungi Nuovo Filtro</h2>
                    <form action="GestioneFiltri" method="post">
                        <input type="hidden" name="action" value="addFilter">
                        <label for="filterName">Nome Filtro:</label>
                        <input type="text" id="filterName" name="filterName" required>

                        <label for="filterValue">Valore Filtro:</label>
                        <input type="text" id="filterValue" name="filterValue" required>

                        <button type="submit" class="btn-submit">Salva Filtro</button>
                    </form>
                </div>
            </div>

            <%-- questo script è per il form  (PS: ci ho perso mezz'ora per capire che ci volesse :,) )--%>
            <script>
                function openAddFilterModal() {
                    document.getElementById("addFilterModal").style.display = "block";
                }
                function closeAddFilterModal() {
                    document.getElementById("addFilterModal").style.display = "none";
                }
            </script>

            <button class="btn-link" onclick="openDateSurveyModal()">Indagine per Data</button>
            <% } else { %>
            <!-- Utente non amministratore: solo il nome utente e il menu a tendina -->
            <div id="userContainer">
                <span class="username" onclick="toggleUserMenu()"><%= utente.getNomeutente() != null ? utente.getNomeutente().toUpperCase() : "" %></span>
                <div id="menuUser" class="user-menu">
                    <ul>
                        <li><a class="btn-link" href="Profilo.jsp">Profilo</a></li>
                        <li><a class="btn-link" href="Impostazioni.jsp">Impostazioni</a></li>
                        <li>
                            <form action="Logout" method="get">
                                <button type="submit">Log-out</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
            <% } %>
            <% } %>
        </div>
    </div>
</header>


<main>

    <%-- variabile boolean per controllare che un filtro è attivo--%>
    <%
        boolean filtroAttivo = (request.getAttribute("Filter") != null && !((String)request.getAttribute("Filter")).isEmpty());
    %>

    <section class="content">
        <div class="banner">Banner</div>

        <%
            if(utente == null){
                String filtro = (String) request.getAttribute("Filter");
                if (filtro != null) {
        %>

        <h2><%= filtro %></h2>

        <div class="product-slider">

            <%
                List<Prodotto> prodottiFiltro = (List<Prodotto>) request.getAttribute("prodottiFiltro");
                for (Prodotto p : prodottiFiltro){
            %>

            <div class="product">
                <div class="box">
                    <h3><%= p.getNome() %></h3>
                    <p>Prezzo: €<%= p.getPrezzo() %></p>
                    <p><%= p.getDescrizione() %></p>
                    <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>
                    <form action="ProductCartMenegment" method="post">
                        <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                        <input type="hidden" name="SourcePage" value="Home">
                        <button type="submit" class="btn-aggiungi">
                            Aggiungi al carrello
                        </button>
                    </form>
                </div>
            </div>

            <%
                    }
                }
            %>

        </div>

        <%
            String filtroAmministratore = (String) request.getAttribute("Filter");
            if (filtroAmministratore != null && utente.isAmministratore()) {
        %>

        <h2><%= filtroAmministratore %></h2>

        <div class="product-slider">

            <%
                List<Prodotto> prodottiFiltro = (List<Prodotto>) request.getAttribute("prodottiFiltro");
                for (Prodotto p : prodottiFiltro){
            %>

            <%-- modifica che permette di visualizzare il pulsante elimina e modifica attraverso il pulsante: "⋮"--%>
            <div class="product">
                <div class="box">
                    <!-- Pulsante ⋮ -->
                    <button class="menu-button" onclick="toggleBoxOption(this)">⋮</button>

                    <!-- Menu opzioni -->
                    <div class="box-option">
                        <button onclick="openEditModal(<%= p.getId_prodotto() %>, '<%= p.getNome() %>', <%= p.getPrezzo() %>, '<%= p.getDescrizione() %>')">Modifica</button>
                        <button onclick="openDeleteModal(<%= p.getId_prodotto() %>)">Elimina</button>
                    </div>

                    <h3><%= p.getNome() %></h3>
                    <p>Prezzo: €<%= p.getPrezzo() %></p>
                    <p><%= p.getDescrizione() %></p>

                    <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>

                    <form action="ProductCartMenegment" method="post">
                        <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                        <input type="hidden" name="SourcePage" value="Home">
                        <button type="submit" class="btn-aggiungi">
                            Aggiungi al carrello
                        </button>
                    </form>
                </div>
            </div>


            <%
                    }
                }
            %>

        </div>
        <%-- utilizzo la variabile boolean per controllare se è stato usato un filtro e se è cosi blocca tutta la parte seguente--%>
        <% if (!filtroAttivo) { %>

        <h2>Novità</h2>
        <div class="product-slider">

                <%
                List<Prodotto> prodottiNovita = (List<Prodotto>) request.getAttribute("prodottiNovita");

                if (prodottiNovita != null) {
                    for (Prodotto p : prodottiNovita) {
            %>
            <div class="product">
                <div class="box">
                    <h3><%= p.getNome() %></h3>
                    <p>Prezzo: €<%= p.getPrezzo() %></p>
                    <p><%= p.getDescrizione() %></p>
                    <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>
                    <form action="ProductCartMenegment" method="post">
                        <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                        <input type="hidden" name="SourcePage" value="Home">
                        <button type="submit" class="btn-aggiungi">
                            Aggiungi al carrello
                        </button>
                    </form>
                </div>
            </div>
                <%
                }
        /**/} else {
            %>
            <p>Nessun prodotto trovato.</p>
                <%
                }
            }else{
            %>

            <div style="display: flex; align-items: center; gap: 10px;">
                <h2>Novità</h2>
                <%--la riga successiva dovrebbe mostrare l'input numerico in novità--%>
                <form action="FiltraNovita" method="get" style="display: flex; align-items: center; gap: 10px;">
                    <input type="number" name="giorni" id="giorni-novita" min="1" max="365" placeholder="Max giorni" required>
                    <button class="btn-link" type="submit">Ricerca</button>
                </form>
            </div>
            <div class="product-slider">

                <%
                    List<Prodotto> prodottiNovita = (List<Prodotto>) request.getAttribute("prodottiNovita");

                    if (prodottiNovita != null) {
                        for (Prodotto p : prodottiNovita) {
                %>

                <%-- modifica che permette di visualizzare il pulsante elimina e modifica attraverso il pulsante: "⋮"--%>
                <div class="product">
                    <div class="box">
                        <!-- Pulsante ⋮ -->
                        <button class="menu-button" onclick="toggleBoxOption(this)">⋮</button>

                        <!-- Menu opzioni -->
                        <div class="box-option">
                            <button onclick="openEditModal(<%= p.getId_prodotto() %>, '<%= p.getNome() %>', <%= p.getPrezzo() %>, '<%= p.getDescrizione() %>')">Modifica</button>
                            <button onclick="openDeleteModal(<%= p.getId_prodotto() %>)">Elimina</button>
                        </div>

                        <h3><%= p.getNome() %></h3>
                        <p>Prezzo: €<%= p.getPrezzo() %></p>
                        <p><%= p.getDescrizione() %></p>

                        <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>

                        <form action="ProductCartMenegment" method="post">
                            <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                            <input type="hidden" name="SourcePage" value="Home">
                            <button type="submit" class="btn-aggiungi">
                                Aggiungi al carrello
                            </button>
                        </form>
                    </div>
                </div>

                <%
                    }
                } else {
                %>
                <p>Nessun prodotto trovato.</p>
                <%
                        }
                    }
                %>

            </div>
                <%
            if(utente == null){
        %>
            <h2>Prodotti più acquistati</h2>
            <div class="product-slider">
                    <%
                List<Prodotto> prodottiPopolari = (List<Prodotto>) request.getAttribute("prodottiPiuAqqistati");
                List<String> paths = (List<String>) request.getAttribute("paths");
                if (prodottiPopolari != null && paths != null) {
                    for (int i = 0; i < prodottiPopolari.size(); i++) {
            %>
                <div class="product-image">
                    <!-- Immagine prodotto (metti il path corretto nell'attributo src) -->
                    <img src=" <%= request.getContextPath() + paths.get(i) %>" alt="Immagine Prodotto">
                </div>
                <div class="product">
                    <div class="box"><h3 ><%= prodottiPopolari.get(i).getNome() %></h3>
                        <p >Prezzo: €<%= prodottiPopolari.get(i).getPrezzo() %></p>
                        <p ><%= prodottiPopolari.get(i).getDescrizione() %></p>
                        <a href="ProdottoS?id=<%= prodottiPopolari.get(i).getId_prodotto() %>">Dettagli</a>
                        <form action="ProductCartMenegment" method="post">
                            <input type="hidden" name="prodottoID" value="<%= prodottiPopolari.get(i).getId_prodotto() %>">
                            <input type="hidden" name="SourcePage" value="Home">
                            <button type="submit" class="btn-aggiungi">
                                Aggiungi al carrello
                            </button>
                        </form>
                    </div>
                </div>
                    <%
                }
            } else {
            %>
                <p>Nessun prodotto trovato.</p>
                    <%
                }
            } else {
            %>

                <div style="display: flex; align-items: center; gap: 10px;">
                    <h2>Prodotti più acquistati</h2>
                    <%--la riga successiva dovrebbe mostrare l'input numerico in prodotti più venduti--%>
                    <form action="FiltraPopolari" method="get" style="display: flex; align-items: center; gap: 10px;">
                        <input type="number" name="maxVendite" id="vendite-max" min="1" placeholder="Num MAX vendite">
                        <button class="btn-link" type="submit">Ricerca</button>
                    </form>
                </div>

                <div class="product-slider">
                        <%
                List<Prodotto> prodottiPopolari = (List<Prodotto>) request.getAttribute("prodottiPiuAqqistati");
                if (prodottiPopolari != null) {
                    for (Prodotto p : prodottiPopolari) {
            %>
                    <%-- modifica che permette di visualizzare il pulsante elimina e modifica attraverso il pulsante: "⋮"--%>
                    <div class="product">
                        <div class="box">
                            <!-- Pulsante ⋮ -->
                            <button class="menu-button" onclick="toggleBoxOption(this)">⋮</button>

                            <!-- Menu opzioni -->
                            <div class="box-option">
                                <button onclick="openEditModal(<%= p.getId_prodotto() %>, '<%= p.getNome() %>', <%= p.getPrezzo() %>, '<%= p.getDescrizione() %>')">Modifica</button>
                                <button onclick="openDeleteModal(<%= p.getId_prodotto() %>)">Elimina</button>
                            </div>

                            <h3><%= p.getNome() %></h3>
                            <p>Prezzo: €<%= p.getPrezzo() %></p>
                            <p><%= p.getDescrizione() %></p>

                            <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>

                            <form action="ProductCartMenegment" method="post">
                                <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                                <input type="hidden" name="SourcePage" value="Home">
                                <button type="submit" class="btn-aggiungi">
                                    Aggiungi al carrello
                                </button>
                            </form>
                        </div>
                    </div>
                        <%
                }
            } else {
            %>
                    <p>Nessun prodotto trovato.</p>
                        <%
                }
            }
            %>

                        <% } %>

    </section>
</main>

<div class="footer-bar">
    <a href="About_Us.jsp" class="btn-link">About Us</a>
    <a href="Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

<%--form per la modifica del prodotto--%>
<div id="editProductModal" class="modal" style="display:none;">
    <div class="modal-content">
        <span class="close" onclick="closeEditModal()">&times;</span>
        <h2>Modifica Prodotto</h2>
        <form action="GestioneProdotti" method="post">
            <input type="hidden" name="action" value="modifica">
            <input type="hidden" name="id_prodotto" id="edit-id">

            <label for="edit-nome">Nome:</label>
            <input type="text" id="edit-nome" name="nome" required>

            <label for="edit-prezzo">Prezzo (€):</label>
            <input type="number" id="edit-prezzo" name="prezzo" step="0.01" required>

            <label for="edit-descrizione">Descrizione:</label>
            <textarea id="edit-descrizione" name="descrizione" required></textarea>

            <button type="submit" class="btn-submit">Salva Modifiche</button>
        </form>
    </div>
</div>

<!-- Script JS per il form della modifica-->
<script>
    function openEditModal(id, nome, prezzo, descrizione) {
        document.getElementById("edit-id").value = id;
        document.getElementById("edit-nome").value = nome;
        document.getElementById("edit-prezzo").value = prezzo;
        document.getElementById("edit-descrizione").value = descrizione;
        document.getElementById("editProductModal").style.display = "block";
    }

    function closeEditModal() {
        document.getElementById("editProductModal").style.display = "none";
    }
</script>

</body>
</html>