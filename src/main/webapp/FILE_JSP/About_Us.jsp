<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 23/04/2025
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DataManagement.Utente" %>
<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>About Us</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
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

        <!-- Informazioni aziendali -->
        <main>
            <section>
                <h2>Chi Siamo</h2>
                <p>
                    Scopri il nostro mondo dedicato ai veri appassionati di musica!
                    Su ISO-16, troverai il miglior merchandise ispirato ai generi musicali più popolari:
                    Rock, Pop, Metal, Jazz, Rap e tanto altro.
                    Siamo il punto di riferimento per tutti i fan di band leggendarie, gruppi iconici e solisti che hanno scritto la storia della musica.
                    Dalle t-shirt ai poster, dalle felpe agli accessori, ogni prodotto è pensato per permetterti di indossare e vivere la tua passione musicale ogni giorno.
                    Unisciti alla nostra community e porta sempre con te il suono dei tuoi artisti preferiti!
                </p>
            </section>

            <section>
                <h2>Missione</h2>
                <p>
                    Su ISO-16, la nostra missione è celebrare la musica in tutte le sue forme.
                    Vogliamo offrire ai fan dei generi musicali più amati un'esperienza unica,
                    fornendo loro merchandise autentico e di qualità, ispirato ai loro artisti preferiti.
                    Siamo appassionati di musica quanto i nostri clienti e ci impegniamo a rendere ogni acquisto un'occasione
                    per esprimere la propria passione e connessione con il mondo musicale.
                    La nostra missione è unire le persone attraverso il potere della musica, fornendo prodotti che riflettano il loro stile e il loro amore per la musica.        </p>
            </section>

            <section>
                <h2>Orari servizio clienti</h2>
                <p><strong>Luned' - Venerdì:</strong> 5:00 - 22:00</p>
                <p><strong>Sabato - Domenica:</strong> 7:00 - 18:00</p>
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
