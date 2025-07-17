<%@ page import="DataManagement.Utente" %>
<a class="btn card-btn" href=${pageContext.request.contextPath}/"carrello">Carrello</a>

<div id="userContainer">
<%--  <span class="username" onclick="toggleUserMenu()">--%>

<%--  </span>--%>
    <span class="username" onclick="toggleUserMenu()">
    <%
        Utente utente = (Utente) session.getAttribute("utente");

        String nomeUtente = utente.getNomeutente().toUpperCase();
    %>
    <%= nomeUtente %>
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
</div>
