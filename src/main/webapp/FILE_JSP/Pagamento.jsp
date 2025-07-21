<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 14/05/2025
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="Model.Utente" %>
<%@ page import="Model.Indirizzo" %>
<%
  // Recupero l'utente loggato dalla sessione
  Utente utente = (Utente) session.getAttribute("utente");
%>
<!DOCTYPE html>
  <html lang="it">
  <head>
    <meta charset="UTF-8">
    <title>Pagamento</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/sfondo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Pagamento.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/Carrello.css">

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pagamento</title>

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
      <h2>Conferma il pagamento</h2>

      <div class="box-container">
        <div class="box-prodotti">
          <%
            // Recupera i prodotti dal database
            ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) session.getAttribute("carrello");
            ArrayList<Integer> quantita = (ArrayList<Integer>) session.getAttribute("Quantità");
            Float prezzotatale = (Float) session.getAttribute("prezzotatale");
            Float spesespedizione = (Float) session.getAttribute("spesespedizione");

            //Visualizza la barra orizzontale per ogni prodotto -->
            if (prodotti != null){
              for (int i = 0; i < prodotti.size(); i++) {
          %>
          <div class="product-bar">
            <!-- Immagine -->
            <!--img src="<//%= prodotto.getImmagine() %>" alt="Immagine prodotto"-->

            <!-- Descrizione -->
            <div class="descrizione">
              <%= prodotti.get(i).getDescrizione() %>
            </div>

            <!-- Prezzo e quantità -->
            <div class="prezzo-quantita">
              <div class="prezzo">€ <%= prodotti.get(i).getPrezzo() %></div>
              <lab for="numero">Scegli un numero:</lab>
              <input onchange="aggiornaQuantita(<%= prodotti.get(i).getId_prodotto()%>,this,<%= prodotti.get(i).getPrezzo()%>)" type="number" id="numero" name="numero" min="0" max="100" step="1" value= <%= quantita.get(i) %>>
            </div>
          </div>
          <%  }
          %>
          <%}%>

        </div>

        <div class="box-pagamenti">
          <!-- BLOCCO PAGAMENTO SOPRA -->
          <h2>Seleziona un metodo di pagamento tra quelli disponibili.</h2>

          <div class="payment-methods">
            <form action="${pageContext.request.contextPath}/PaymentAutorization" method="post">
              <button>
                <img src="https://img.icons8.com/color/48/paypal.png" alt="PayPal" title="PayPal">
              </button>
            </form>
            <img src="https://img.icons8.com/color/48/visa.png" alt="Visa" title="Visa">
            <img src="https://img.icons8.com/color/48/mastercard.png" alt="MasterCard" title="MasterCard">
            <img src="https://img.icons8.com/color/48/amex.png" alt="American Express" title="American Express">
            <img src="https://img.icons8.com/color/48/discover.png" alt="Discover" title="Discover">
            <img src="https://img.icons8.com/color/48/apple-pay.png" alt="Apple Pay" title="Apple Pay">
            <img src="https://img.icons8.com/color/48/google-pay.png" alt="Google Pay" title="Google Pay">
          </div>

          <!-- BLOCCO INDIRIZZO SOTTO -->
          <div class="select-ardress">
            <h2>Seleziona indirizzo</h2>

            <%
              List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("listaIndirizzi");
            %>

            <label for="indirizzo">Indirizzo consegna:</label>
            <select id="indirizzo" name="indirizzo" required onchange="SelectIndirizzo(this)">
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
          </div> <!-- fine select-ardress -->
        </div> <!-- fine box-acquista -->


      <%
        String esito = (String) request.getAttribute("esitoPagamento");
        if ("successo".equals(esito)) {
      %>
      <div class="payment-message success">Pagamento effettuato con successo!</div>
      <%
      } else if ("errore".equals(esito)) {
      %>
      <div class="payment-message error">Errore durante il pagamento. Riprova.</div>
      <%
        }
      %>

    </main>

    <script>

        function SelectIndirizzo(select){
            if (select.value){
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '${pageContext.request.contextPath}/SelectIndirizzo';

                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'indirizzoConsegnia';
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
