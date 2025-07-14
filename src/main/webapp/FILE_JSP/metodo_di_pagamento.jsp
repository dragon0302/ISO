<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 01/04/2025
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Metodo_di_pagamento</title></head>
<body>

<form action="logIN" method="POST">

  <%--@declare id="NumeroCarta"--%>
  <%--@declare id="DataScadenza"--%>
  <%--@declare id="CVV"--%>
  <%--@declare id="Tipo"--%>
  <%--@declare id="Default_pagamento"--%>
  <%--@declare id="CF_utente"--%>
  <%--input type="hidden" name="action" value="insert"--%>

  <label for="NumeroCarta">Numero della carta<textarea name="NumeroCarta" id="NumeroCarta" required placeholder="Inserire il numero della carta"></textarea></label>

  <label for="DataScadenza">Data di scadenza carta<textarea name="DataScadenza" id="DataScadenza" required placeholder="Inserire la data di scadenza della carta"></textarea></label>

  <label for="CVV">CVV dell'utente<textarea name="CVV" id="CVV" required placeholder="Iserire il CVV dell'utente"></textarea></label>

  <label>Tipo:</label>
  <label><input type="radio" name="Tipo" value="Prepagata" required> Carta prepagata</label>
  <label><input type="radio" name="Tipo" value="Debito" required> Carta di debito</label>
  <label><input type="radio" name="Tipo" value="Credito" required> Carta di credito</label>

  <label>Default_pagamento:</label>
  <label><input type="radio" name="Default_pagamento" value="1" required> Y</label>
  <label><input type="radio" name="Default_pagamento" value="0" required> N</label>

  <label for="CF_utente">CF dell'utente<textarea name="CF_utente" id="CF_utente" maxlength="16" required placeholder="Inserire CF dell'utente"></textarea> </label>

  <input type="submit" value="add">

</form>
</body>
</html>
