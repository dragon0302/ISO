<%@ page import="DataManagement.Prodotto" %>

<%
    Prodotto p = (Prodotto) request.getAttribute("prodotto");
    Boolean admin = (Boolean) request.getAttribute("isAmministratore");
%>
<div class="product">
    <div class="box">
        <% if (admin != null) { %>
        <button class="menu-button" onclick="toggleBoxOption(this)">⋮</button>
        <div class="box-option">
            <button onclick="openEditModal(<%= p.getId_prodotto() %>, '<%= p.getNome() %>', <%= p.getPrezzo() %>, '<%= p.getDescrizione() %>', '<%=p.%>')">Modifica</button>
            <button onclick="openDeleteModal(<%= p.getId_prodotto() %>)">Elimina</button>
        </div>
        <% } %>

        <h3><%= p.getNome() %></h3>
        <p>Prezzo: € <%= p.getPrezzo() %></p>
        <p><%= p.getDescrizione() %></p>
        <a href="ProdottoS?id=<%= p.getId_prodotto() %>">Dettagli</a>

        <form action="ProductCartMenegment" method="post">
            <input type="hidden" name="prodottoID" value="<%= p.getId_prodotto() %>">
            <input type="hidden" name="SourcePage" value="Home">
            <button type="submit" class="btn-aggiungi">Aggiungi al carrello</button>
        </form>
    </div>
</div>

