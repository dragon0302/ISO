<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 14/05/2025
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="com.mysql.cj.Session" %>
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
  <p>Seleziona un metodo di pagamento tra quelli disponibili.</p>

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

  <label>
    <input type="checkbox" name="Fatturazione" value="Accetta">
    Vuoi usare l'indirizzo di fatturazione per la consegna?
  </label>

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


<div class="footer-bar">
  <a href="${pageContext.request.contextPath}/FILE_JSP/About_Us.jsp" class="btn-link">About Us</a>
  <a href="${pageContext.request.contextPath}/FILE_JSP/Contattaci.jsp" class="btn-link">Contattaci</a>
  <a href="${pageContext.request.contextPath}/FILE_JSP/Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
  <a href="${pageContext.request.contextPath}/FILE_JSP/Assistenza.jsp" class="btn-link">Assistenza</a>
</div>

</body>
</html>
