<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 06/05/2025
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page import="DataManagement.Utente" %>
<%@ page import="DataManagement.Ordine" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Lista Ordini</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Lista_ordini.css">
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

        <div class="box-all_order">
            <h2>I miei ordini</h2>
            <%
                if (utente != null) {
                    List<Ordine> ordini = (List<Ordine>) session.getAttribute("ordine");
                    List<Prodotto> prodotti = new ArrayList<>();

                    System.out.println(ordini == null);
                    if (ordini != null && !ordini.isEmpty()) {
                        List<List<Prodotto>> Listaprodotti = (List<List<Prodotto>>) session.getAttribute("ListeProdotti");
                        for (int i = 0;i < ordini.size(); i++) {
                          System.out.println("numero ordine: " + ordini.get(i).getIdOrdine());
            %>
            <div class="order-box">
                <div class="order-info">
                    <p><strong>Ordine #</strong><%= ordini.get(i).getIdOrdine() %></p>
                    <p><strong>Data:</strong> <%= ordini.get(i).getData_ordine() %></p>
                    <p><strong>Totale:</strong> € <%= ordini.get(i).getTotale() %></p>
                    <%--<p><strong>Stato:</strong> <%= ordini.get(i).getStato() %></p>--%>


                <div class="order-actions">
                    <a href="RiacquistaProdotti.jsp?idOrdine=<%= ordini.get(i).getIdOrdine() %>" class="btn-small">Riacquista Prodotti</a>
                    <%--<a href="VediOrdine.jsp?idOrdine=<%= ordine.getId() %>" class="btn-small">Vedi Ordine</a>--%>
                </div>

                <%

                          //prendere la funzione che da i prodotti dell'ordine e creare classe apposita per la gesione delle immagini
                    prodotti = Listaprodotti.get(i);
                            for (int g = 0; g < prodotti.size(); g++){
                              System.out.println("Numero Prodotto: " + prodotti.get(g).getId_prodotto());

                            /*String imgPath = (prodotti != null && !prodotti.isEmpty())
                                    ? prodotti.get(0).getImmagine() // es. "img/prodotto1.jpg"
                                    : "img/default.jpg"; // immagine placeholder*/
                %>
                <%--<div class="order-image">
                    <img src="<%= imgPath %>" alt="Immagine prodotto" />
                </div>--%>

                    <div class="product">
                        <div class="box">
                            <h3><%= prodotti.get(g).getNome() %></h3>
                            <p>Prezzo: € <%= prodotti.get(g).getPrezzo() %></p>
                            <p><%= prodotti.get(g).getDescrizione() %></p>
                            <a href="ProdottoS?id=<%= prodotti.get(g).getId_prodotto() %>">Dettagli</a>

                            <form action="ProductCartMenegment" method="post">
                                <input type="hidden" name="prodottoID" value="<%= prodotti.get(g).getId_prodotto() %>">
                                <input type="hidden" name="SourcePage" value="Home">
                                <button type="submit" class="btn-aggiungi">Aggiungi al carrello</button>
                            </form>
                        </div>
                    </div>

            <%      }%>
                </div>
            </div>
                <% }
            %>


            <%}else { %>
            <p>Non hai ancora effettuato ordini.</p>
            <%      }
            } else {
            %>
            <p>Effettua il <a href="Log_in.jsp">login</a> per visualizzare i tuoi ordini.</p>
            <% } %>
        </div>

</main>

<div class="footer-bar">
    <a href="${pageContext.request.contextPath}/FILE_JSP/About_Us.jsp" class="btn-link">About Us</a>
    <a href="${pageContext.request.contextPath}/FILE_JSP/Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="${pageContext.request.contextPath}/FILE_JSP/Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="${pageContext.request.contextPath}/FILE_JSP/Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

</body>
</html>
