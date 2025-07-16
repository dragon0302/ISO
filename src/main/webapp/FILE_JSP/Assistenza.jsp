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
        <title>Assistenza</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Assistenza.css">
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

        <main>
            <div class="faq-container">
                <div class="faq-item">
                    <h3>Come posso effettuare un ordine?</h3>
                    <p>Per ordinare, cerca i tuoi prodotti preferiti, aggiungili al carrello e segui la procedura di checkout.</p>
                </div>

                <div class="faq-item">
                    <h3>Quali metodi di pagamento accettate?</h3>
                    <p>Accettiamo carte di credito/debito e PayPal.</p>
                </div>

                <div class="faq-item">
                    <h3>Quanto tempo impiega la consegna?</h3>
                    <p>Generalmente, le consegne avvengono entro 3-5 giorni lavorativi.</p>
                </div>

                <div class="faq-item">
                    <h3>Posso restituire un prodotto?</h3>
                    <p>Sì, hai 14 giorni per effettuare il reso. Visita la sezione "Profilo" per gestire i tuoi resi.</p>
                </div>

                <div class="faq-item">
                    <h3>Come posso contattare l’assistenza?</h3>
                    <p>Puoi usare il nostro modulo nella pagina Contattaci o inviarci un'email a <strong>support@iso16.it</strong>.</p>
                </div>

                <div class="contact-link">
                    <a href="Contattaci.jsp">Contatta il supporto</a>
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