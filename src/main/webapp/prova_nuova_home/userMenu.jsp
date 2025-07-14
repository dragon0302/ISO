<%@ page import="DataManagement.Utente" %>

<%
    Utente utente = (Utente) session.getAttribute("utente");

    String nomeUtente = utente.getNomeutente().toUpperCase();
%>

<a class="btn card-btn" href="carrello">Carrello</a>

<div id="userContainer">
<%--  <span class="username" onclick="toggleUserMenu()">--%>

<%--  </span>--%>
    <span class="username" onclick="toggleUserMenu()"><%= utente.getNomeutente()!= null ? utente.getNomeutente().toUpperCase() : "" %></span>
    <div id="menuUser" class="user-menu">
        <ul>
            <li><a class="btn-link" href="${pageContext.request.contextPath}/FILE_JSP/Profilo.jsp">Profilo</a></li>
            <li><a class="btn-link" href="${pageContext.request.contextPath}/FILE_JSP/Impostazioni.jsp">Impostazioni</a></li>
            <li>
                <form action="Logout" method="get">
                    <button type="submit">Log-out</button>
                </form>
            </li>
        </ul>
    </div>
</div>
