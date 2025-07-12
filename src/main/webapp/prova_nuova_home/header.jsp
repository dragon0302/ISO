<%@ page import="DataManagement.Utente" %>
<%
  Utente utente = (Utente) session.getAttribute("utente");
  Boolean admin = (Boolean) request.getAttribute("isAmministratore");
%>

<div class="top-header">
  <div class="logo-container">
    <a href="${pageContext.request.contextPath}/Catalogo">
      <img src="<%= request.getContextPath() + "/Immagini/isologo.png" %>" alt="Immagine Prodotto">
    </a>
  </div>

  <div class="center-section">
    <div class="search-container">
      <input type="text" id="searchInput" placeholder="Cerca prodotti..." onkeyup="showSuggestions(this.value)">
      <div class="suggestions" id="suggestionBox"></div>
    </div>

    <form id="filterForm" action="Catalogo" method="post">
      <input type="hidden" id="action" name="action" value="">
      <input type="hidden" id="value" name="value" value="">
    </form>

    <jsp:include page="/prova_nuova_home/filterBar.jsp" />
  </div>

  <div class="right-section">
    <% if (utente == null) { %>
    <jsp:include page="/prova_nuova_home/logButtons.jsp" />
    <% } else if (!admin && admin != null) { %>
<%--    <jsp:include page="/prova_nuova_home/userMenu.jsp" />--%>
    <jsp:include page="/prova_nuova_home/logButtons.jsp" />
    <% } else { %>
    <jsp:include page="/prova_nuova_home/adminButtons.jsp" />
    <% } %>
  </div>

</div>