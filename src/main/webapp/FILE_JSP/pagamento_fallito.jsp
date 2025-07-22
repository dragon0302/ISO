<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pagamento Fallito</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/FILE_CSS/pagamento_fallito.css">
    <script>
        // Countdown di 60 secondi
        let seconds = 60;
        function updateCountdown() {
            const el = document.getElementById("countdown");
            if (seconds > 0) {
                el.textContent = seconds;
                seconds--;
                setTimeout(updateCountdown, 1000);
            } else {
                window.location.href = "<%= request.getContextPath() %>/";
            }
        }
        window.onload = updateCountdown;
    </script>
</head>
<body>

<!-- Immagine in cima -->
<img src="<%= request.getContextPath() %>/Immagini/pagamentoFallito.jpg" alt="Pagamento Fallito" class="image-top">

<!-- Box contenuto -->
<div class="success-box">
    <h1>Pagamento Fallito</h1>
    <p>Si è verificato un errore durante la transazione.</p>
    <p>Verrai reindirizzato alla home tra <strong id="countdown">60</strong> secondi.</p>
    <p>Oppure puoi <a href="<%= request.getContextPath() %>/Catalogo">tornarci subito</a>.</p>
</div>

</body>
</html>
