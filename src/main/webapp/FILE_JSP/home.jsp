 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DataManagement.Prodotto" %>
<%@ page import="DataManagement.Utente" %>

<%
    // Recupero l'utente loggato dalla sessione
    Utente utente = (Utente) session.getAttribute("utente");
    // Controllo se c'è un filtro attivo
    String filtro = (String) request.getAttribute("Filter");
    boolean filtroAttivo = filtro != null && !filtro.isEmpty();
    List<Prodotto> prodottiFiltro = (List<Prodotto>) request.getAttribute("prodottiFiltro");
    List<String> paths = (List<String>) application.getAttribute("Paths");
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>ISO-16</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/home.css">

        <script>
            function toggleBoxOption(btn) {
                const menu = btn.nextElementSibling;
                menu.classList.toggle("show");
            }
            //per modificare
            function openEditModal(id, nome, prezzo, descrizione, filto) {
                alert("Modifica prodotto: " + nome);
            }
            //per cancellare
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

            <section class="content">
                <div class="banner">
                    <span>Banner</span>
                </div>

                <% if(utente != null && utente.isAmministratore()){
                %>
                <button class="banner-btn" onclick="openAddProductModal()">+</button>

                <%--form per la creazione del prodotto--%>
                <div id="addProductModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeAddProductModal()">&times;</span>
                        <h2>Aggiungi Prodotto</h2>
                        <form action="ProductCatalogoMenegment" method="post">

                            <label for="imageInput">Aggiungi immagine</label>
                            <input type="file" name="immagine" id="imageInput" accept="image/*" required>
                            <img id="imagePreview" src="#" alt="Anteprima immagine" style="display:none; width: 100px; margin-top:10px;"/>

                            <label for="productName">Nome prodotto:</label>
                            <input type="hidden" name="action" value="nuovo">
                            <input type="text" id="productName" name="productName" required><br><br>

                            <label for="price">Prezzo (€):</label>
                            <input type="number" id="price" name="price" step="0.01" required><br><br>

                            <label for="edit-descrizione">Descrizione:</label>
                            <input type="text" id="productDescrizione" name="productDescrizione" required><br><br>

                            <label for="edit-filtro">filtro</label>
                            <input type="text" id="productfiltro" name="filtro" required>

                            <button type="submit">Salva</button>
                        </form>
                    </div>
                </div>
                <%
                    }
                %>
                <%-- FILTRO ATTIVO --%>
                <%
                    if (filtroAttivo && prodottiFiltro != null) {
                %>
                <h2><%= filtro %></h2>
                <div class="product-slider">
                    <% for (Prodotto p : prodottiFiltro) { %>
                    <%
                    request.setAttribute("prodotto", p);
                    request.setAttribute("path", paths.get(p.getId_prodotto() - 1));
                        if(utente == null){
                            request.setAttribute("isAmministratore", null);
                        }else{
                            request.setAttribute("isAmministratore", utente.isAmministratore());
                        }
                    %>
                    <jsp:include page="/FILE_JSP/prodottoBox.jsp" />

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
                    <form action="${pageContext.request.contextPath}/Catalogo" method="get">
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
                            request.setAttribute("path", paths.get(p.getId_prodotto() - 1));
                                if(utente == null){
                                    request.setAttribute("isAmministratore", null);
                                }else{
                                    request.setAttribute("isAmministratore", utente.isAmministratore());
                                }
                    %>
                    <jsp:include page="/FILE_JSP/prodottoBox.jsp"/>


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
                    <form action="${pageContext.request.contextPath}/Catalogo" method="get">
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
                            request.setAttribute("path", paths.get(p.getId_prodotto() - 1));
                                if(utente == null){
                                    request.setAttribute("isAmministratore", null);
                                }else{
                                    request.setAttribute("isAmministratore", utente.isAmministratore());
                                }
                    %>
                    <jsp:include page="/FILE_JSP/prodottoBox.jsp" />

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
                <form action="ProductCatalogoMenegment" method="post">
                    <input type="hidden" name="action" value="modifica">
                    <input type="hidden" name="id_prodotto" id="edit-id">

                    <label for="edit-nome">Nome:</label>
                    <input type="text" id="edit-nome" name="nome" required>

                    <label for="edit-prezzo">Prezzo (€):</label>
                    <input type="number" id="edit-prezzo" name="prezzo" step="0.01" required>

                    <label for="edit-descrizione">Descrizione:</label>
                    <textarea id="edit-descrizione" name="descrizione" required></textarea>

                    <label for="edit-filtro">filtro</label>
                    <input type="text" id="edit-filtro" name="filtro" required>

                    <button type="submit" class="btn-submit">Salva Modifiche</button>
                </form>
            </div>
        </div>



        <script>
            function openAddProductModal() {
                document.getElementById('addProductModal').style.display = 'flex';
            }

            function closeAddProductModal() {
                document.getElementById('addProductModal').style.display = 'none';
            }

            // Opzionale: chiusura cliccando fuori dal modal
            window.onclick = function(event) {
                const modal = document.getElementById("addProductModal");
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            }

            const imageInput = document.getElementById('imageInput');
            const imagePreview = document.getElementById('imagePreview');

            imageInput.addEventListener('change', function () {
                const file = this.files[0];
                if (file) {
                    imagePreview.src = URL.createObjectURL(file);
                    imagePreview.style.display = 'block';
                }
            });
        </script>


        <!-- Script JS per il form della modifica-->
        <script>
            function openEditModal(id, nome, prezzo, descrizione, filtro) {
                document.getElementById("edit-id").value = id;
                document.getElementById("edit-nome").value = nome;
                document.getElementById("edit-prezzo").value = prezzo;
                document.getElementById("edit-descrizione").value = descrizione;
                // document.getCategoriaById("edit-filtro").value = filtro;
                document.getElementById("editProductModal").style.display = "block";
            }

            function closeEditModal() {
                document.getElementById("editProductModal").style.display = "none";
            }
        </script>
    </body>
</html>