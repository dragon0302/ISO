<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page import="Model.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Carrello.css">
</head>
<body>

<script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
<script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

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

    <div class="box-container">
        <div class="box-prodotti">
            <%
            // Recupera i prodotti dal database
            ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) session.getAttribute("carrello");
            ArrayList<Integer> quantita = (ArrayList<Integer>) session.getAttribute("Quantità");
            Float prezzotatale = (Float) session.getAttribute("prezzotatale");

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
                    <input onchange="aggiornaQuantita(<%= prodotti.get(i).getId_prodotto()%>,this,<%= prodotti.get(i).getPrezzo()%>)" type="number" id="numero" name="numero" min="0" max="100" step="1" value= <%= quantita.get(i) %>>
            </div>
        </div>
            <%  }
                %>

            <div>
                <div class="PT">Prezzo Totale</div>
                <div id="prezzo-totale" class="prezzo-totale"><%= prezzotatale%> </div>
            </div>
        <%}%>

            </div>


        <div class="box-acquista">
            <form action="${pageContext.request.contextPath}/CheckUtente" method="post">
                <button type="submit" class="btn-aggiungi">
                    Vai al pagamento
                </button>
            </form>
        </div>
    </div>

<div class="footer-bar">
    <a href="${pageContext.request.contextPath}/FILE_JSP/About_Us.jsp" class="btn-link">About Us</a>
    <a href="${pageContext.request.contextPath}/FILE_JSP/Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="${pageContext.request.contextPath}/FILE_JSP/Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="${pageContext.request.contextPath}/FILE_JSP/Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

</body>
</html>





