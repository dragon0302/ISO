<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 11:13
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
        <title>Sig-up</title>
        <!-- Link al file CSS esterno per lo styling della pagina-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Sign-up.css">
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
            <div class="content-wrapper">
                <!-- Immagini laterali fisse -->
                <!-- img src="left-image.png" class="side-image left" alt="Immagine sinistra" -->
                <!-- img src="right-image.png" class="side-image right" alt="Immagine destra" -->

                <%
                    String erroreCF = (String) request.getAttribute("erroreCF");
                    String erroreNomeUtente = (String) request.getAttribute("erroreNomeUtente");
                    String erroreData = (String) request.getAttribute("erroreData");
                    String erroreEmail = (String) request.getAttribute("erroreEmail");
                    String erroreNomeRegex = (String) request.getAttribute("erroreNomeRegex");
                    String erroreCognomeRegex = (String) request.getAttribute("erroreCognomeRegex");
                    String erroreEmailRegex = (String) request.getAttribute("erroreEmailRegex");
                    String erroreNomeUtenteRegex = (String) request.getAttribute("erroreNomeUtenteRegex");
                    String errorePasswordRegex = (String) request.getAttribute("errorePasswordRegex");
                %>

                <!-- Box centrale con form -->
                <div class="form-container">
                    <h2>Registrazione Utente</h2>
                    <form action="${pageContext.request.contextPath}/Sign-up" method="POST">
                        <input type="hidden" name="action" value="insert">

                        <div class="form-group">
                            <label for="CodiceFiscale">Codice Fiscale *</label>
                            <input type="text" name="CodiceFiscale" id="CodiceFiscale" required>
                            <% if(erroreCF != null){ %>
                                <%= erroreCF%>
                            <%}%>
                        </div>

                        <div class="form-group">
                            <label for="NomeUtente">Nome Utente *</label>
                            <input type="text" name="NomeUtente" id="NomeUtente" required>
                            <% if(erroreNomeUtente != null){ %>
                                <%= erroreNomeUtente%>
                            <%} else if (erroreNomeUtenteRegex != null) {%>
                                <%= erroreNomeUtenteRegex%>
                            <%}%>
                        </div>

                        <div class="form-group">
                            <label for="Password">Password *</label>
                            <input type="password" name="Password" id="Password" required>
                            <small className="password-requirements">
                                La password deve contenere almeno 8 caratteri, una lettera maiuscola, un numero e un carattere speciale("!@#$%^&*()_").
                            </small>
                            <small id="password-feedback"></small>
                            <% if(errorePasswordRegex != null){ %>
                                <%= errorePasswordRegex%>
                            <%}%>
                        </div>

                        <div class="form-group">
                            <label for="Nome">Nome *</label>
                            <input type="text" name="Nome" id="Nome" required>
                            <% if(erroreNomeRegex != null){ %>
                                <%= erroreNomeRegex%>
                            <%}%>
                        </div>

                        <div class="form-group">
                            <label for="Cognome">Cognome *</label>
                            <input type="text" name="Cognome" id="Cognome" required>
                            <% if(erroreCognomeRegex != null){ %>
                                <%= erroreCognomeRegex%>
                            <%}%>
                        </div>

                        <div class="form-group">
                            <label>Sesso *</label>
                            <label>M<input type="radio" name="sesso" value="M" required></label>
                            <label>F<input type="radio" name="sesso" value="F" required></label>
                        </div>

                        <div class="form-group">
                            <label for="DataNascita">Data di Nascita *</label>
                            <input type="date" name="DataNascita" id="DataNascita" required>
                            <% if(erroreData != null){ %>
                                <%= erroreData%>
                            <%}%>
                        </div>

                        <div class="form-group">
                            <label for="E-mail">E-mail *</label>
                            <input type="email" name="E-mail" id="E-mail" required>
                            <% if(erroreEmail != null){ %>
                                <%= erroreEmail%>
                            <%} else if (erroreEmailRegex != null) {%>
                            <%=erroreEmailRegex%>
                            <%}%>
                            %>
                        </div>

                        <div class="form-group full-width">
                            <input type="submit" value="Registrati">
<%--     aggiungere questo redirect   (response.sendRedirect("login.jsp");)--%>
                        </div>
                    </form>
                </div>
            </div>
        </main>

    </body>
</html>
