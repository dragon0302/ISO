<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 21/07/2025
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Pagamento riuscito</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/FILE_CSS/pagamento_riuscito.css">
</head>
<body>

<!-- Immagine in cima -->
<img src="<%= request.getContextPath() %>/Immagini/pagamentoRiuscito.jpg" alt="Pagamento Completato" class="image-top">

<!-- Box contenuto -->
<div class="success-box">
  <h1>Pagamento Completato</h1>
  <p>Il tuo pagamento è andato a buon fine. Grazie per la fiducia!</p>
  <p>Per concludere il pagamento torna alla home.</p>
  <a href="<%= request.getContextPath() %>/NewOrder">Torna alla Home</a>
  <p>Nel caso non si torni alla home l'ordine non verrà salvato.</p>
</div>

</body>
</html>
