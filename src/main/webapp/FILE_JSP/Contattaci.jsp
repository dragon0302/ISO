<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 23/04/2025
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
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
        <title>Contattaci</title>

        <!-- Link al file CSS esterno per lo styling della pagina-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
        <!-- Link al file CSS esterno per lo styling dell'elenco-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Contattaci.css">

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

        <!-- MAIN CONTENT -->
        <main>
            <section class="contact-info">
                <!-- Titolo della sezione di contatto -->
                <h1>Contattaci</h1>

                <!-- Descrizione generale per la sezione di contatto -->
                <p>For any questions, problems or requests, we are at your disposal. Contact us using one of the following telephone numbers:</p>
                <p>Per qualsiasi domanda, problema o richiesta, siamo a vostra disposizione. Contattateci utilizzando uno dei seguenti numeri di telefono:</p>

                <!-- Elenco delle referenze telefoniche -->
                <ul>
                    <li><strong>Telefono referente Italiano:</strong> +39 XXX XXX XXXX</li>
                    <li><strong>English contact telephone:</strong> +44 XXX XXX XXXX</li>
                    <li><strong>Téléphone de contact français:</strong> +33 XXX XXX XXXX</li>
                    <li><strong>Deutsches Kontakttelefon:</strong> +49 XXX XXX XXX</li>
                </ul>
            </section>
        </main>

        <!-- FOOTER -->
        <div class="footer-bar">
            <a href="${pageContext.request.contextPath}/FILE_JSP/About_Us.jsp" class="btn-link">About Us</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Contattaci.jsp" class="btn-link">Contattaci</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Assistenza.jsp" class="btn-link">Assistenza</a>
        </div>
    </body>
</html>
