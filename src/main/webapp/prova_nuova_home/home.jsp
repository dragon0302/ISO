 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="DataManagement.Utente" %>

<%
    // Recupero l'utente loggato dalla sessione
    Object utente = session.getAttribute("utente");

    // Controllo se c'è un filtro attivo
    String filtro = (String) request.getAttribute("Filter");
    boolean filtroAttivo = filtro != null && !filtro.isEmpty();
    List<Prodotto> prodottiFiltro = (List<Prodotto>) request.getAttribute("prodottiFiltro");
    boolean admin = Boolean.parseBoolean((String) request.getAttribute("admin"));
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>ISO-16</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/sfondo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/prova_nuova_home/home.css">

        <script>
            function toggleBoxOption(btn) {
                const menu = btn.nextElementSibling;
                menu.classList.toggle("show");
            }

            function openEditModal(id, nome, prezzo, descrizione) {
                alert("Modifica prodotto: " + nome);
            }

            function openDeleteModal(id) {
                alert("Elimina prodotto con ID: " + id);
            }

            window.addEventListener("click", function(e) {
                document.querySelectorAll(".box-option.show").forEach(menu => {
                    if (!menu.previousElementSibling.contains(e.target)) {
                        menu.classList.remove("show");
                    }
                });
            });
        </script>

    </head>
    <body>
        <script src="${pageContext.request.contextPath}/Javascript/CatalogFilter.js"></script>
        <script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
        <script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

        <header>

            <div class="top-header">
                <!-- Parte 1 - Logo a sinistra -->
                <div class="logo-container">
                    <a href="Catalogo">
                        <img src=" <%= request.getContextPath() + "/Immagini/isologo.png" %>" alt="Immagine Prodotto">
                    </a>
                </div>

                <div>
                    <%@ include file="/prova_nuova_home/header.jsp" %>

                    <div class="right-section">
                        <% if (utente == null) { %>
                        <jsp:include page="/prova_nuova_home/logButtons.jsp" />
                        <% } else if (admin) { %>
                        <jsp:include page="/prova_nuova_home/adminButtons.jsp" />
                        <% } else { %>
                        <jsp:include page="/prova_nuova_home/userMenu.jsp" />
                        <% } %>
                    </div>
                </div>
            </div> <!-- fine top-header -->
        </header>

        <main>

            <section class="content">
                <div class="banner">Banner</div>

                <%-- FILTRO ATTIVO --%>
                <%
                    if (filtroAttivo && prodottiFiltro != null) {
                %>
                <h2><%= filtro %></h2>
                <div class="product-slider">
                    <% for (Prodotto p : prodottiFiltro) { %>
                    <%
                    request.setAttribute("prodotto", p);
                    request.setAttribute("admin", admin);
                    %>
                    <jsp:include page="/prova_nuova_home/prodottoBox.jsp" />

                    <% } %>
                </div>
                <%
                    }
                %>

                <%-- SE NON C'È UN FILTRO, MOSTRO IL RESTO --%>
                <%
                    if (!filtroAttivo) {
                %>

                <%-- NOVITÀ --%>
                <div class="section-header">
                    <h2>Novità</h2>
                    <form action="FiltraNovita" method="get">
                        <input type="number" name="giorni" min="1" max="365" placeholder="Max giorni" required>
                        <button class="btn-link" type="submit">Ricerca</button>
                    </form>
                </div>
                <div class="product-slider">
                    <%
                        List<Prodotto> prodottiNovita = (List<Prodotto>) request.getAttribute("prodottiNovita");
                        if (prodottiNovita != null) {
                            for (Prodotto p : prodottiNovita) {
                            request.setAttribute("prodotto", p);
                            request.setAttribute("admin", admin);
                    %>
                    <jsp:include page="/prova_nuova_home/prodottoBox.jsp"/>


                    <%
                        }
                    } else {
                    %>
                    <p>Nessun prodotto trovato.</p>
                    <%
                        }
                    %>
                </div>

                <%-- PRODOTTI POPOLARI --%>
                <div class="section-header">
                    <h2>Prodotti più acquistati</h2>
                    <form action="FiltraPopolari" method="get">
                        <input type="number" name="maxVendite" placeholder="Num MAX vendite">
                        <button class="btn-link" type="submit">Ricerca</button>
                    </form>
                </div>
                <div class="product-slider">
                    <%
                        List<Prodotto> prodottiPopolari = (List<Prodotto>) request.getAttribute("prodottiPiuAqqistati");
                        if (prodottiPopolari != null) {
                            for (Prodotto p : prodottiPopolari) {
                            request.setAttribute("prodotto", p);
                            request.setAttribute("admin", admin);
                    %>
                    <jsp:include page="/prova_nuova_home/prodottoBox.jsp" />

                    <%
                        }
                    } else {
                    %>
                    <p>Nessun prodotto trovato.</p>
                    <%
                        }
                    %>
                </div>

                <% } %>

            </section>

        </main>

        <div class="footer-bar">
            <a href="${pageContext.request.contextPath}/FILE_JSP/About_Us.jsp" class="btn-link">About Us</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Contattaci.jsp" class="btn-link">Contattaci</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
            <a href="${pageContext.request.contextPath}/FILE_JSP/Assistenza.jsp" class="btn-link">Assistenza</a>
        </div>

        <%--form per la modifica del prodotto--%>
        <div id="editProductModal" class="modal" style="display:none;">
            <div class="modal-content">
                <span class="close" onclick="closeEditModal()">&times;</span>
                <h2>Modifica Prodotto</h2>
                <form action="GestioneProdotti" method="post">
                    <input type="hidden" name="action" value="modifica">
                    <input type="hidden" name="id_prodotto" id="edit-id">

                    <label for="edit-nome">Nome:</label>
                    <input type="text" id="edit-nome" name="nome" required>

                    <label for="edit-prezzo">Prezzo (€):</label>
                    <input type="number" id="edit-prezzo" name="prezzo" step="0.01" required>

                    <label for="edit-descrizione">Descrizione:</label>
                    <textarea id="edit-descrizione" name="descrizione" required></textarea>

                    <button type="submit" class="btn-submit">Salva Modifiche</button>
                </form>
            </div>
        </div>

        <!-- Script JS per il form della modifica-->
        <script>
            function openEditModal(id, nome, prezzo, descrizione) {
                document.getElementById("edit-id").value = id;
                document.getElementById("edit-nome").value = nome;
                document.getElementById("edit-prezzo").value = prezzo;
                document.getElementById("edit-descrizione").value = descrizione;
                document.getElementById("editProductModal").style.display = "block";
            }

            function closeEditModal() {
                document.getElementById("editProductModal").style.display = "none";
            }
        </script>
    </body>
</html>