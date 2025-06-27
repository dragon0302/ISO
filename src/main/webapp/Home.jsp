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
    <section class="content">
        <div class="banner">Banner</div>
        <%
            if(utente == null){
        %>
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
            } else {
            %>
            <p>Nessun prodotto trovato.</p>
            <%
                }
            }else{
            %>

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
                                <button class="btn-elimina" onclick="openDeleteModal()">
                                    Elimina
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
                  System.out.println(paths.size());
                    for (int i = 0; i < prodottiPopolari.size(); i++) {
                      System.out.println(paths.get(i));
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

                    <h2>Prodotti più acquistati</h2>
                    <div class="product-slider">
                            <%
                List<Prodotto> prodottiPopolari = (List<Prodotto>) request.getAttribute("prodottiPiuAqqistati");
                if (prodottiPopolari != null) {
                    for (Prodotto p : prodottiPopolari) {
            %>
                        <div class="product">
                            <div class="box"><h3 ><%= p.getNome() %></h3>
                                <p >Prezzo: €<%= p.getPrezzo() %></p>
                                <p ><%= p.getDescrizione() %></p>
                                <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>
                                <form action="ProductCartMenegment" method="post">
                                    <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
                                    <input type="hidden" name="SourcePage" value="Home">
                                    <button type="submit" class="btn-aggiungi">
                                        Aggiungi al carrello
                                    </button>
                                    <button class="btn-elimina" onclick="openDeleteModal()">
                                        Elimina
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

    </section>
</main>

<div class="footer-bar">
    <a href="About_Us.jsp" class="btn-link">About Us</a>
    <a href="Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

</body>
</html>