<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>prova del carello</title>
</head>
<body>
<h2>Catalogo</h2>
<h3>Inserimento</h3>

<form action="logIN" method="POST">

    <%--@declare id="nomeutente"--%>
    <%--@declare id="password"--%>
    <%--@declare id="Nome"--%>
    <%--@declare id="Cognome"--%>
    <%--@declare id="Sesso"--%>
    <%--@declare id="DataNascita"--%>
    <%--@declare id="Amministratore"--%>
    <%--input type="hidden" name="action" value="insert"--%>

    <label for="NomeUtente">User<textarea name="NomeUtente" id="NomeUtente" maxlength="50" required placeholder="Inserire il proprio user"></textarea></label>

    <label for="Password">password<textarea name="PW" maxlength="255"></textarea></label>

    <label for="Nome">Nome<textarea></textarea></label>

    <label for="Cognome">Cognome<textarea></textarea></label>

    <label for="Sesso">Sesso<textarea></textarea></label>

    <label for="DataNascita">...<textarea></textarea></label>

    <label for="Amministratore">...<textarea></textarea></label>

    <input type="submit" value="add">
</form>

<%

    String messagio = (String) request.getAttribute("messagio");
    if (messagio != null && !messagio.isEmpty()){
%>

<p><%=messagio%></p>

<%}%>

</body>
</html>
