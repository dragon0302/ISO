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

    <form action="Utente" method="POST">

    <%--@declare id="CodiceFiscale"--%>
    <%--@declare id="nomeutente"--%>
    <%--@declare id="password"--%>
    <%--@declare id="Nome"--%>
    <%--@declare id="Cognome"--%>
    <%--@declare id="Sesso"--%>
    <%--@declare id="DataNascita"--%>
    <%--@declare id="Amministratore"--%>

    <input type="hidden" name="action" value="insert">

    <label for="CodiceFiscale">Codice Fiscale<input name="CodiceFiscale" id="CodiceFiscale" maxlength="50" required placeholder="Inserire il proprio user"></input></label>

    <%
        String errore = (String) request.getAttribute("errore");
        if(errore != null && !errore.isEmpty()){
    %>

        <p><%=errore%></p>

        <%}%>

    <label for="NomeUtente">User<input name="NomeUtente" id="NomeUtente" maxlength="50" required placeholder="Inserire il proprio user"></input></label>

    <label for="Password">password<input name="Password" id="Password" maxlength="255" required placeholder="Inserire la Password"></input></label>

    <label for="Nome">Nome<input name="Nome" id="Nome" maxlength="50" required placeholder="Inserire il proprio nome"></input></label>

    <label for="Cognome">Cognome<input name="Cognome" id="Cognome" maxlength="50" required placeholder="Inserire il proprio cognome"></input></label>

    <label>Sesso:</label>
    <label><input type="radio" name="sesso" value="M" required> M</label>
    <label><input type="radio" name="sesso" value="F" required> F</label>

    <label for="DataNascita">Data di Nascita<input type="date" name="DataNascita" id="DataNascita" required placeholder="Inserire la data di nascita"></input></label>

    <label>Amministratore:</label>
    <label><input type="radio" name="Amministratore" value="1" required> Y</label>
    <label><input type="radio" name="Amministratore" value="0" required> N</label>

    <input type="submit" value="add">
    </form>

    <footer>
        <a href="#">About Us</a>
        <a href="#">Contattaci</a>
        <a href="#">Termini e condizioni</a>
        <a href="#">Assistenza</a>
    </footer>
</body>
</html>
