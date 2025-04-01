<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Prova Prodotto</title>
</head>
<body>

<form action="logIN" method="POST">

    <%--@declare id="ID_Prodotto"--%>
    <%--@declare id="Nome"--%>
    <%--@declare id="MediaValutazione"--%>
    <%--@declare id="Taglia"--%>
    <%--@declare id="Descrizione"--%>
    <%--@declare id="Categoria"--%>
    <%--input type="hidden" name="action" value="insert"--%>

    <label for="ID_Prodotto">User<textarea name="ID_Prodotto" id="ID_Prodotto" required placeholder="Inserire l'ID del Prodotto"></textarea></label>

    <label for="Nome">Nome Prodotto<textarea name="Nome" id="Nome" maxlength="20" required placeholder="Inserire il nome del prodotto"></textarea></label>

    <label for="MediaValutazione">Media Valutazione<textarea name="MediaValutazione" id="MediaValutazione" required placeholder="Iserire la Media delle valutazioni"></textarea></label>

    <label for="Taglia">Taglia prodotto<textarea name="Taglia" id="Taglia" maxlength="3" required placeholder="Inserire taglia del prodotto"></textarea></label>

    <label for="Descrizione">Descrizione<textarea name="Descrizione" id="Descrizione" maxlength="500" required placeholder="Inserire la desscrizione del prodotto"></textarea></label>

    <label for="Categoria">Categoria<textarea name="Categoria" id="Categoria" maxlength="20" required placeholder="Inserire la Categoria del prodotto"></textarea></label>

    <input type="submit" value="add">

</form>
</body>
</html>
