<%@ page import="DataManagement.Utente" %>
<div id="userContainer">
  <span class="username" onclick="toggleUserMenu()">
    <%
      Utente utente = (Utente) session.getAttribute("utente");

      String nomeUtente = utente.getNomeutente().toUpperCase();
    %>
  </span>

  <div id="userMenu" class="user-menu">
    <ul>
      <li><a href="${pageContext.request.contextPath}/FILE_JSP/Profilo.jsp">Profilo</a></li>
      <li><a href="${pageContext.request.contextPath}/FILE_JSP/Impostazioni.jsp">Impostazioni</a></li>
      <form action="Logout" method="get">
        <li><button>Log-out</button></li>
      </form>
    </ul>
  </div>

  <button class="btn-link" onclick="openAddProductModal()">Aggiungi Prodotto</button>
  <button class="btn-link" onclick="openAddFilterModal()">Aggiungi Filtro</button>

  <div id="addFilterModal" class="modal" style="display:none;">
    <div class="modal-content">
      <span class="close" onclick="closeAddFilterModal()">&times;</span>
      <h2>Aggiungi Nuovo Filtro</h2>
      <form action="GestioneFiltri" method="post">
        <input type="hidden" name="action" value="addFilter">
        <label for="filterName">Nome Filtro:</label>
        <input type="text" id="filterName" name="filterName" required>
        <label for="filterValue">Valore Filtro:</label>
        <input type="text" id="filterValue" name="filterValue" required>
        <button type="submit" class="btn-submit">Salva Filtro</button>
      </form>
    </div>
  </div>

  <script>
    function openAddFilterModal() {
      document.getElementById("addFilterModal").style.display = "block";
    }
    function closeAddFilterModal() {
      document.getElementById("addFilterModal").style.display = "none";
    }
  </script>
</div>
