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
<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>Profilo Utente</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Profilo.css">

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

            <div class="profile-container">
                <!-- Immagine profilo -->
                <div class="profile-image-section">
                    <!--img src="img/default-profile.png" alt="Immagine Profilo"-->
                </div>

                <!-- Informazioni -->
                <div class="profile-info-section">
                    <form action="AggiornaProfilo" method="post" class="profile-form">
                        <!-- Email e Nome Utente -->
                        <div class="form-row">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" value="<%= utente.getEmail() %>" required>
                            </div>

                            <div class="form-group">
                                <label for="username">Nome Utente</label>
                                <input type="text" name="username" id="username" value="<%= utente.getNomeutente() %>" required>
                            </div>
                        </div>

                        <!-- Password -->
                        <div class="form-group">
                            <label for="password">Password (lascia vuoto per non cambiare)</label>
                            <input type="password" name="password" id="password" placeholder="Nuova password">
                        </div>

                        <button type="submit" class="btn btn-save">Salva Modifiche</button>
                    </form>
                </div>
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
