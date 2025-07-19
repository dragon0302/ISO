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
<%@ page import="Model.Utente" %>
<%@ page import="Model.Indirizzo" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.MetodoPagamento" %>

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
                    <jsp:include page="/FILE_JSP/header.jsp" />
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
                        <h3>Modifica nome o e-mail:</h3>
                        <a href="${pageContext.request.contextPath}/FILE_JSP/Profilo.jsp" class="button-link">Modifica</a>

                    <!-- Indirizzo di fatturazione -->

                    <%
                        List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("listaIndirizzi");
                    %>


                    <label for="indirizzo">Indirizzo di fatturazione:</label>
                    <select id="indirizzo" name="indirizzo" required onchange="selectFatturazione(this)">
                        <option value="">-- Seleziona un indirizzo --</option>

                        <%
                            if(indirizzi != null){
                                for (Indirizzo i : indirizzi){
                        %>

                        <option value="<%= i.getID_Indirizzo()%>">
                            <%= i.getCap()%> <%= i.getCittà()%> <%= i.getCivico()%> <%= i.getVia()%>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- SEZIONE 2: IMPOSTAZIONI ACQUISTO -->
                <div class="settings-section">
                    <h2>Impostazioni Acquisto</h2>

                    <!-- Metodo di pagamento preferito -->
                    <div class="settings-option">
                        <label>Metodo di pagamento preferito</label>

                            <select name="metodoPagamentoId" required onchange="pagamentoDefault(this)">
                                <option value="">--- Seleziona un metodo ---</option>
                                <%
                                    List<MetodoPagamento> metodi = (List<MetodoPagamento>) request.getAttribute("metodiPagamento");
                                    if(metodi != null){
                                        for(MetodoPagamento m : metodi){
                                %>

                                <option value="<%= m.getNumerocarta()%>">
                                    **** **** **** <%=m.getUltime4Cifre()%> (scadenza: <%=m.getDataScadenza()%>) (Tipo: <%=m.getTipo()%>)
                                </option>

                                <%
                                        }
                                    }
                                %>
                            </select>

                        <a href="${pageContext.request.contextPath}/FILE_JSP/metodo_di_pagamento.jsp" class="button-link">Aggiungi</a>
                    </div>

                    <!-- Storico ordini -->
                    <form action="${pageContext.request.contextPath}/Ordini" method="GET" class="settings-option">
                        <label>Visualizza storico ordini:</label>
                        <button type="submit">Visualizza</button>
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

                    <!-- Elimina account -->
                    <form action="${pageContext.request.contextPath}/DeleteAccount" method="POST" class="settings-option" onsubmit="return confirm('Sei sicuro di voler eliminare l\'account?');">
                        <label>Elimina account</label>
                        <button type="submit" class="delete-btn">Elimina</button>
                    </form>
                </div>

            </div>
        </main>

        <script>

            function selectFatturazione(select){
                if (select.value){
                    const form = document.createElement('form');
                    form.method = 'POST';
                    form.action = '${pageContext.request.contextPath}/selectDefault';

                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'indirizzoFatturazione';
                    input.value = select.value;

                    form.appendChild(input);
                    document.body.appendChild(form);
                    form.submit();
                }
            }

        </script>
        <script>

            function pagamentoDefault(select){
                if (select.value){
                    const form = document.createElement('form');
                    form.method = 'POST';
                    form.action = '${pageContext.request.contextPath}/selectDefault';

                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'pagamentoDefault';
                    input.value = select.value;

                    form.appendChild(input);
                    document.body.appendChild(form);
                    form.submit();
                }
            }

        </script>


        <div class="footer-bar">
            <a href="${pageContext.request.contextPath}/FILE_JSP/About_Us.jsp" class="btn-link">About Us</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Contattaci.jsp" class="btn-link">Contattaci</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Assistenza.jsp" class="btn-link">Assistenza</a>
        </div>
    </body>
</html>
