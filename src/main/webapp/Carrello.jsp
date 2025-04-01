<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Prova Carrello</title></head>
<body>

<form action="logIN" method="POST">

    <%--@declare id="ID_Carrello"--%>
    <%--@declare id="CF_utente"--%>
    <%--input type="hidden" name="action" value="insert"--%>

    <label for="ID_Carrello">ID del carrello<textarea name="ID_Carrello" id="ID_Carrello" required placeholder="Inserire l'ID del carrello"></textarea></label>

    <label for="CF_utente">CF dell'utente<textarea name="CF_utente" id="CF_utente" maxlength="16" required placeholder="Inserire CF dell'utente"></textarea> </label>

    <input type="submit" value="add">

</form>
</body>
</html>
