<%@ page import="Model.Prodotto" %>

<%
    Prodotto p = (Prodotto) request.getAttribute("prodotto");
    Boolean admin = (Boolean) request.getAttribute("isAmministratore");
    String path = (String) request.getAttribute("path");
%>
<div class="product">
    <div class="box">
        <% if (admin != null) { %>
        <button class="menu-button" onclick="toggleBoxOption(this)">&#8942;</button>
        <div class="box-option">
            <button onclick="openEditModal(<%= p.getId_prodotto() %>, '<%= p.getNome() %>', <%= p.getPrezzo() %>, '<%= p.getDescrizione() %>', '<%=p.getCategoria()%>')">Modifica</button>
            <form action="eliminaProdotto" method="POST" class="settings-option" onsubmit="return confirm('Sei sicuro di voler eliminare il prodotto?');">
                <button onclick="openDeleteModal(<%= p.getId_prodotto() %>)">Elimina</button>
            </form>
        </div>
        <% } %>

        <div class="product-image">
            <!-- Immagine prodotto (metti il path corretto nell'attributo src) -->
            <img src=" <%= request.getContextPath() + path %>" alt="Immagine Prodotto">
        </div>

        <h3><%= p.getNome() %></h3>
        <p>Prezzo: <%= p.getPrezzo() %> &euro;</p>
        <p><%= p.getDescrizione() %></p>
        <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>

        <form action="ProductCartMenegment" method="post">
            <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
            <input type="hidden" name="SourcePage" value="Home">
            <button type="submit" class="btn-aggiungi">Aggiungi al carrello</button>
        </form>
    </div>
</div>

