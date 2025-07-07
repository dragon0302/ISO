<%--
  Created by IntelliJ IDEA.
  User: PRINCIPALE
  Date: 23/04/2025
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us</title>
    <link rel="stylesheet" href="sfondo.css">
</head>
<body>

<script src="${pageContext.request.contextPath}/Javascript/Barra_di_ricerca.js"></script>
<script src="${pageContext.request.contextPath}/Javascript/Barra_ricerca_function.js"></script>

<!-- Top Header -->
<header>
    <div class="top-header">
        <!-- Parte 1 - Logo a sinistra -->
        <div class="logo-container">
            <a href="Home.jsp">
                <img src=" <%= request.getContextPath() + "/Immagini/isologo.png" %>" alt="Immagine Prodotto">
            </a>
        </div>

        <!-- Parte 2 - Barra di ricerca centrata e filtri sotto -->
        <div class="center-section">
            <!-- Barra di ricerca -->
            <div class="search-container">
                <input type="text" id="searchInput" placeholder="Cerca prodotti..." onkeyup="showSuggestions(this.value)">
                <div class="suggestions" id="suggestionBox"></div>
            </div>
            <!-- Filtri sotto la barra di ricerca -->
            <div class="filter-bar">
                <div class="filter">
                    <a href="#">ROCK</a>
                    <div class="dropdown">
                        <a href="#">AC/DC</a>
                        <a href="#">Aerosmith</a>
                        <a href="#">Led Zeppelin</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">POP</a>
                    <div class="dropdown">
                        <a href="#">Adele</a>
                        <a href="#">Beyoncé</a>
                        <a href="#">Madonna</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">JAZZ</a>
                    <div class="dropdown">
                        <a href="#">Coltrane</a>
                        <a href="#">Davis</a>
                        <a href="#">Fitzgerald</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">METAL</a>
                    <div class="dropdown">
                        <a href="#">Iron Maiden</a>
                        <a href="#">Metallica</a>
                        <a href="#">Slayer</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">RAP</a>
                    <div class="dropdown">
                        <a href="#">Drake</a>
                        <a href="#">Eminem</a>
                        <a href="#">Kanye</a>
                    </div>
                </div>
                <div class="filter">
                    <a href="#">Abbigliamento</a>
                    <div class="dropdown">
                        <a href="#">Cappelli</a>
                        <a href="#">Felpe</a>
                        <a href="#">T-shirt</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Parte 3 - Bottoni di Sign-up e Log-in a destra -->
        <div class="right-section">
            <a class="btn" href="Carrello.jsp">Carrello</a>
            <a class="btn login-btn" href="Log_in.jsp">Log In</a>
            <a class="btn signup-btn" href="Sign-up.jsp">Sign Up</a>
        </div>
    </div>
</header>

<!-- Informazioni aziendali -->
<main>
    <section>
        <h2>Chi Siamo</h2>
        <p>
            Scopri il nostro mondo dedicato ai veri appassionati di musica!
            Su ISO-16, troverai il miglior merchandise ispirato ai generi musicali più popolari:
            Rock, Pop, Metal, Jazz, Rap e tanto altro.
            Siamo il punto di riferimento per tutti i fan di band leggendarie, gruppi iconici e solisti che hanno scritto la storia della musica.
            Dalle t-shirt ai poster, dalle felpe agli accessori, ogni prodotto è pensato per permetterti di indossare e vivere la tua passione musicale ogni giorno.
            Unisciti alla nostra community e porta sempre con te il suono dei tuoi artisti preferiti!
        </p>
    </section>

    <section>
        <h2>Missione</h2>
        <p>
            Su ISO-16, la nostra missione è celebrare la musica in tutte le sue forme.
            Vogliamo offrire ai fan dei generi musicali più amati un'esperienza unica,
            fornendo loro merchandise autentico e di qualità, ispirato ai loro artisti preferiti.
            Siamo appassionati di musica quanto i nostri clienti e ci impegniamo a rendere ogni acquisto un'occasione
            per esprimere la propria passione e connessione con il mondo musicale.
            La nostra missione è unire le persone attraverso il potere della musica, fornendo prodotti che riflettano il loro stile e il loro amore per la musica.        </p>
    </section>

    <section>
        <h2>Orari servizio clienti</h2>
        <p><strong>Lunedì - Venerdì:</strong> 5:00 - 22:00</p>
        <p><strong>Sabato - Domenica:</strong> 7:00 - 18:00</p>
    </section>

</main>

<!-- Footer -->
<div class="footer-bar">
    <a href="About_Us.jsp" class="btn-link">About Us</a>
    <a href="Contattaci.jsp" class="btn-link">Contattaci</a>
    <a href="Termini_e_condizioni.jsp" class="btn-link">Termini e condizioni</a>
    <a href="Assistenza.jsp" class="btn-link">Assistenza</a>
</div>
</body>
</html>
