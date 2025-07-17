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
<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prodotto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Prodotto.css">
</head>


<script src="${pageContext.request.contextPath}/Javascript/Passweor_error.js"></script>
<script src="${pageContext.request.contextPath}/Javascript/CatalogFilter.js"></script>
<script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
<script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

<header>
    <div class="top-header">
        <div>
            <%
                if(utente == null){
                    request.setAttribute("isAmministratore", null);
                }else{
                    request.setAttribute("isAmministratore", utente.isAmministratore());
                }
            %>
            <jsp:include page="/FILE_JSP/header.jsp" />
        </div>
    </div> <!-- fine top-header -->
</header>

<body>

<%
    Prodotto prodotto = (Prodotto) request.getAttribute("Prodotto");
    if (prodotto != null){
%>

<div class="product-container">
    <div class="product-image">
        <!-- Immagine prodotto (metti il path corretto nell'attributo src) -->
        <!--img src="<!%= prodotto.getImmaginePath() != null ? prodotto.getImmaginePath() : "placeholder.jpg" %>" alt="Immagine Prodotto"-->
    </div>
    <div class="product-details">
        <h2 class="product-name"><%= prodotto.getNome() %></h2>
        <p class="product-description"><%= prodotto.getDescrizione() %></p>
        <div class="product-info">
            <span class="product-price">€ <%= prodotto.getPrezzo() %></span>
            <form action="ProductCartMenegment" method="post" class="add-to-cart-form">
                <input type="hidden" name="prodottoID" value="<%= prodotto.getId_prodotto() %>">
                <input type="hidden" name="SourcePage" value="Prodotto">
                <label for="quantity">Quantità:</label>
                <input type="number" id="quantity" name="quantita" value="1" min="1">
                <button type="submit" class="btn">Aggiungi al carrello</button>
            </form>
        </div>
    </div>
</div>
<%
    }
%>


</body>
</html>
