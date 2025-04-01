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
    <title>Prova Utente</title>
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

    <label for="Password">password<textarea name="Password" id="Password" maxlength="255" required placeholder="Inserire la Password"></textarea></label>

    <label for="Nome">Nome<textarea name="Nome" id="Nome" maxlength="50" required placeholder="Inserire il proprio nome"></textarea></label>

    <label for="Cognome">Cognome<textarea name="Cognome" id="Cognome" maxlength="50" required placeholder="Inserire il proprio cognome"></textarea></label>

    <label>Sesso:</label>
    <label><input type="radio" name="sesso" value="M" required> M</label>
    <label><input type="radio" name="sesso" value="F" required> F</label>

    <label for="DataNascita">Data di Nascita<textarea name="DataNascita" id="DataNascita" required placeholder="Inserire la data di nascita"></textarea></label>

    <label>Amministratore:</label>
    <label><input type="radio" name="Amministratore" value="1" required> Y</label>
    <label><input type="radio" name="Amministratore" value="0" required> N</label>

    <input type="submit" value="add">
    </form>
</body>
</html>
