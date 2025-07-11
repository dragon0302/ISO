<%@ page import="DataManagement.Utente" %>
<div id="userContainer">
  <span class="username" onclick="toggleUserMenu()">
    <%
        Utente utente = (Utente) session.getAttribute("utente");

        String nomeUtente = utente.getNomeutente().toUpperCase();
    %>
  </span>
    <div id="menuUser" class="user-menu">
        <ul>
            <li><a class="btn-link" href="Profilo.jsp">Profilo</a></li>
            <li><a class="btn-link" href="Impostazioni.jsp">Impostazioni</a></li>
            <li>
                <form action="Logout" method="get">
                    <button type="submit">Log-out</button>
                </form>
            </li>
        </ul>
    </div>
</div>
