<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DataManagement.Utente" %>
<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Prova Ordine</title>
</head>
<body>

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

<form action="logIN" method="POST">

    <%--@declare id="ID_Ordine"--%>
    <%--@declare id="Data_Ordine"--%>
    <%--@declare id="Prezzo_tot"--%>
    <%--@declare id="Lista_Prodotti"--%>
    <%--@declare id="ID_Carrello"--%>
    <%--input type="hidden" name="action" value="insert"--%>

    <label for="ID_Ordine">ID dell'ordine<textarea name="ID_Ordine" id="ID_Ordine" required placeholder="Inserire l'ID dell'ordine"></textarea></label>

    <label for="Data_Ordine">Data dell'ordine<textarea name="Data_Ordine" id="Data_Ordine" required placeholder="Inserire la data dell'ordine"></textarea></label>

    <label for="Prezzo_tot">Prezzo totale<textarea name="Prezzo_tot" id="Prezzo_tot" required placeholder="Iserire il prezzo totale"></textarea></label>

    <label for="Lista_Prodotti">Lista dei Prodotti<textarea name="Lista_Prodotti" id="Lista_Prodotti" maxlength="500" required placeholder="Inserire la lista dei prodotti"></textarea></label>

    <label for="ID_Carrello">ID del carrello<textarea name="ID_Carrello" id="ID_Carrello" required placeholder="Inserire l'ID del carrello"></textarea></label>

    <input type="submit" value="add">

</form>
</body>
</html>
