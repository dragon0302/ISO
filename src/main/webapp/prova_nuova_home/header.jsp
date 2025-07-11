<div class="top-header">
  <div class="logo-container">
    <a href="Catalogo">
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
</div>