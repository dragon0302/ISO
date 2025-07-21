<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 21/07/2025
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Errore 404 - Pagina non trovata</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/FILE_CSS/errore404.css">
</head>
<body>

<div class="error-container">
    <img src="<%= request.getContextPath() %>/Immagini/immagine404error.jpg" alt="404 image" class="error-image">
    <div class="error-text">404</div>
</div>

<div class="error-box">
    <p>La pagina che stai cercando non esiste o è stata rimossa.</p>
    <a href="<%= request.getContextPath() %>/Catalogo">Torna alla Home</a>
</div>

</body>
</html>

