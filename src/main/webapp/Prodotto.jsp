<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="DataManagement.Utente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prodotto</title>
</head>
<body>

<%
    Utente utente = (Utente) request.getAttribute("Utente");
    Prodotto prodotto = (Prodotto) request.getAttribute("Prodotto");

    if (prodotto != null){
        %>

<h2>Dettagli Prodotto</h2>
<p><strong>Nome:</strong> <%= prodotto.getNome() %></p>
<p><strong>Media Valutazione:</strong> <%= prodotto.getMedia_valutazione() %></p>
<p><strong>Taglia:</strong> <%= prodotto.getTaglia() %></p>
<p><strong>Descrizione:</strong> <%= prodotto.getDescrizione() %></p>
<p><strong>Categoria:</strong> <%= prodotto.getCategoria() %></p>
<p><strong>Prezzo:</strong> <%= prodotto.getPrezzo() %></p>
<form action="Carrello" method="post">
<!--input type="hidden" name="CF_Utente" value="<//%= utente.getCf() %>"-->
<input type="hidden" name="prodottoID" value="<%= prodotto.getId_prodotto() %>">
<button type="submit" class="btn">Aggiungi al carrelo</button>
</form>
<%
}
%>


</body>
</html>
