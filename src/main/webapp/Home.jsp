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
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <header>
            <div class="logo">Logo</div>
            <nav>
                <input type="text" placeholder="Cerca..." class="search-bar">
                <a href="#">Log-in</a>
                <a href="#">Carrello</a>
            </nav>
        </header>

        <main>
            <aside class="hide-sidebar">
                <h3>Categorie dei prodotti</h3>
                <ul>
                    <li> Categoria 1 </li>
                    <li> Categoria 2 </li>
                    <li> Categoria 3 </li>
                </ul>
            </aside>

            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Prezzo (€)</th>
                <th>Descrizione</th>
            </tr>

            <section class="content">
                <div class="banner">Banner</div>

                <h2>Novità</h2>
                <div class="product-slider">
                    <%
                        List<Prodotto> prodottiNovita = (List<Prodotto>) request.getAttribute("prodottiNovita");
                        if (prodottiNovita != null) {
                            for (Prodotto p : prodottiNovita) {
                    %>
                    <div class="product">
                        <h3><%= p.getNome() %></h3>
                        <%-- <p>Prezzo: €<%= p.getPrezzo() %></p> --%>
                        <p><%= p.getDescrizione() %></p>
                        <a href="prodotto.jsp?id=<%= p.getId_prodotto() %>">Dettagli</a>
                    </div>
                    <%
                            }
                        } else {
                    %>
                    <p>Nessun prodotto trovato.</p>
                    <%
                        }
                    %>
                </div>

                <h2>Prodotti più acquistati</h2>
                <div class="product-slider">
                    <%
                        List<Prodotto> prodottiPopolari = (List<Prodotto>) request.getAttribute("prodottiPopolari");
                        if (prodottiPopolari != null) {
                            for (Prodotto p : prodottiPopolari) {
                    %>
                    <div class="product">
                        <h3><%= p.getNome() %></h3>
                        <%-- <p>Prezzo: €<%= p.getPrezzo() %></p> --%>
                        <p><%= p.getDescrizione() %></p>
                        <a href="prodotto.jsp?id=<%= p.getId_prodotto() %>">Dettagli</a>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <p>Nessun prodotto trovato.</p>
                    <%
                        }
                    %>
                </div>

            </section>
        </main>

        <footer>
            <a href="#">About Us</a>
            <a href="#">Contattaci</a>
            <a href="#">Termini e condizioni</a>
            <a href="#">Assistenza</a>
        </footer>

    </body>
</html>
