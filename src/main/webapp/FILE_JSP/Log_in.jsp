<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Utente" %>
<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Log_in.css">
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

<main>
    <div class="page-container">
        <!-- img src="left-image.png" alt="Immagine Sinistra" class="side-image" -->

        <div class="login-box">
            <h2>Accedi</h2>
            <form action="${pageContext.request.contextPath}/Login" method="POST">
                <div class="form-group">
                    <label for="username">Nome Utente</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <input type="submit" value="Accedi">
                </div>
                <%
                    String Errore_USER_PASS = (String) request.getAttribute("Errore_USER_PASS");
                    if (Errore_USER_PASS != null) {
                %>
                <div class="field-error"><%= Errore_USER_PASS %></div>
                <% } %>
            </form>
        </div>

        <!-- img src="right-image.png" alt="Immagine Destra" class="side-image" -->
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




