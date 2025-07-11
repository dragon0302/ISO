<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Prova Indirizzo</title></head>
<body>

  <form action="logIN" method="POST">

    <%--@declare id="ID_Indirizzo"--%>
    <%--@declare id="Città"--%>
    <%--@declare id="Provincia"--%>
    <%--@declare id="CAP"--%>
    <%--@declare id="Via"--%>
    <%--@declare id="Civico"--%>
    <%--@declare id="Scala"--%>
    <%--@declare id="Indirizzo2"--%>
    <%--@declare id="Note"--%>
    <%--@declare id="Fatturazione"--%>
    <%--@declare id="CF_utente"--%>
    <%--input type="hidden" name="action" value="insert"--%>

    <label for="ID_Indirizzo">ID dell'indirizzo<textarea name="ID_Indirizzo" id="ID_Indirizzo" required placeholder="Inserire l'ID dell'indirizzo"></textarea></label>

    <label for="Città">Città<textarea name="Città" id="Città" maxlength="50" required placeholder="Inserire la Città di residenza"></textarea></label>

    <label for="Provincia">Provincia<textarea name="Provincia" id="Provincia" maxlength="50" required placeholder="Iserire la Provincia"></textarea></label>

    <label for="CAP">CAP<textarea name="CAP" id="CAP" maxlength="5" required placeholder="Inserire il CAP"></textarea> </label>

    <label for="Via">Via<textarea name="Via" id="Via" maxlength="100" required placeholder="Inserire la Via di residenza"></textarea> </label>

    <label for="Civico">Civico<textarea name="Civico" id="Civico" required placeholder="Inserire il Civico dell'abitazione"></textarea> </label>

    <label for="Scala">Scala del palazzo/civico (lettera) della via<textarea name="Scala" id="Scala" required placeholder="Inserire scala se presente (ES: civico:123 Scala:1/A)"></textarea> </label>

    <label for="Indirizzo2">Indirizzo2<textarea name="Indirizzo2" id="Indirizzo2" maxlength="100" required placeholder="Inserire eventuali altre informazioni sull'indirizzo"></textarea> </label>

    <label for="Note">Note<textarea name="Note" id="Note" maxlength="255" required placeholder="Inserire eventuali note per il corriere"></textarea> </label>

    <label>Fatturazione:</label>
    <label><input type="radio" name="Fatturazione" value="1" required> Y</label>
    <label><input type="radio" name="Fatturazione" value="0" required> N</label>

    <label for="CF_utente">CF dell'utente<textarea name="CF_utente" id="CF_utente" maxlength="16" required placeholder="Inserire CF dell'utente"></textarea> </label>

    <input type="submit" value="add">

</form>
</body>
</html>

