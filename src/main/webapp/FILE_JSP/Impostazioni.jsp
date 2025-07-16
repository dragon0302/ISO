<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 02/04/2025
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 02/04/2025
  Time: 11:28
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
        <title>Impostazioni</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Impostazioni.css">
    <body>

        <script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
        <script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

        <<script src="${pageContext.request.contextPath}/Javascript/CatalogFilter.js"></script>
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
                    <jsp:include page="/prova_nuova_home/header.jsp" />
                </div>
            </div> <!-- fine top-header -->
        </header>


        <main>

            <!-- BOX PRINCIPALE -->
            <div class="settings-box">

                <!-- SEZIONE 1: IMPOSTAZIONI ACCOUNT -->
                <div class="settings-section">
                    <h2>Impostazioni Account</h2>

                    <!-- Modifica dati account -->
                    <form action="modificaDatiAccount" method="POST" class="settings-option">
                        <label for="nome">Nome:</label>
                        <input type="text" id="nome" name="nome" required>

                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>
                        <a href="${pageContext.request.contextPath}/FILE_JSP/Profilo.jsp" class="button-link">Modifica</a>

                    </form>

                    <!-- Indirizzo di fatturazione -->
                    <form action="gestisciFatturazione" method="POST" class="settings-option">
                        <label for="indirizzo">Indirizzo di fatturazione:</label>
                        <input type="text" id="indirizzo" name="indirizzo" required>

                        <button type="submit" class="button-link">Gestisci</button>
                    </form>
                </div>

                <!-- SEZIONE 2: IMPOSTAZIONI ACQUISTO -->
                <div class="settings-section">
                    <h2>Impostazioni Acquisto</h2>

                    <!-- Metodo di pagamento preferito -->
                    <div class="settings-option">
                        <label>Metodo di pagamento preferito</label>
                        <a href="${pageContext.request.contextPath}/FILE_JSP/metodo_di_pagamento.jsp" class="button-link">Imposta</a>
                    </div>

                    <!-- Storico ordini -->
                    <form action="visualizzaOrdini" method="GET" class="settings-option">
                        <label>Visualizza storico ordini:</label>
                        <button type="submit">Visualizza</button>
                    </form>

                    <!-- Fatturazione elettronica -->
                    <form action="gestisciFatturazioneElettronica" method="POST" class="settings-option">
                        <label for="pec">PEC:</label>
                        <input type="email" id="pec" name="pec">

                        <label for="codice_univoco">Codice univoco:</label>
                        <input type="text" id="codice_univoco" name="codice_univoco" maxlength="7">

                        <button type="submit">Gestisci</button>
                    </form>
                </div>

                <!-- SEZIONE 3: NOTIFICHE -->
                <div class="settings-section">
                    <h2>Notifiche</h2>

                    <!-- Notifica stato ordini -->
                    <form action="aggiornaNotificaOrdini" method="POST" class="settings-option">
                        <label for="notifica_ordini">Stato ordini e spedizioni</label>
                        <input type="checkbox" id="notifica_ordini" name="notifica_ordini" checked>
                        <button type="submit">Salva</button>
                    </form>

                    <!-- Notifica promozioni -->
                    <form action="aggiornaNotificaPromo" method="POST" class="settings-option">
                        <label for="notifica_promo">Avvisi di promozioni o offerte</label>
                        <input type="checkbox" id="notifica_promo" name="notifica_promo">
                        <button type="submit">Salva</button>
                    </form>
                </div>

                <!-- SEZIONE 4: PRIVACY E SICUREZZA -->
                <div class="settings-section">
                    <h2>Privacy e Sicurezza</h2>

                    <!-- Gestione cookie -->
                    <form action="modificaCookie" method="POST" class="settings-option">
                        <label>Gestione consensi cookie</label>
                        <button type="submit">Modifica</button>
                    </form>

                    <!-- Scarica dati -->
                    <form action="scaricaDati" method="POST" class="settings-option">
                        <label>Scarica i tuoi dati</label>
                        <button type="submit" class="download-btn">Scarica</button>
                    </form>

                    <!-- Elimina account -->
                    <form action="eliminaAccount" method="POST" class="settings-option" onsubmit="return confirm('Sei sicuro di voler eliminare l\'account?');">
                        <label>Elimina account</label>
                        <button type="submit" class="delete-btn">Elimina</button>
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
