const products = [
    // AC/DC
    "AC/DC T-Shirt", "AC/DC CD", "AC/DC Hoodie", "AC/DC Vinyl", "AC/DC Poster", "AC/DC Cap", "AC/DC Mug",

    // Adele
    "Adele T-Shirt", "Adele CD", "Adele Hoodie", "Adele Vinyl", "Adele Poster", "Adele Cap", "Adele Mug",

    // Metallica
    "Metallica T-Shirt", "Metallica CD", "Metallica Hoodie", "Metallica Vinyl", "Metallica Poster", "Metallica Cap", "Metallica Mug",

    // Queen
    "Queen T-Shirt", "Queen CD", "Queen Hoodie", "Queen Vinyl", "Queen Poster", "Queen Cap", "Queen Mug",

    // Eminem
    "Eminem T-Shirt", "Eminem CD", "Eminem Hoodie", "Eminem Vinyl", "Eminem Poster", "Eminem Cap", "Eminem Mug",

    // Taylor Swift
    "Taylor Swift T-Shirt", "Taylor Swift CD", "Taylor Swift Hoodie", "Taylor Swift Vinyl", "Taylor Swift Poster", "Taylor Swift Cap", "Taylor Swift Mug",

    // Iron Maiden
    "Iron Maiden T-Shirt", "Iron Maiden CD", "Iron Maiden Hoodie", "Iron Maiden Vinyl", "Iron Maiden Poster", "Iron Maiden Cap", "Iron Maiden Mug",

    // Led Zeppelin
    "Led Zeppelin T-Shirt", "Led Zeppelin CD", "Led Zeppelin Hoodie", "Led Zeppelin Vinyl", "Led Zeppelin Poster", "Led Zeppelin Cap", "Led Zeppelin Mug"
    ];

    function showSuggestions(value) {
    const suggestionBox = document.getElementById('suggestionBox');
    suggestionBox.innerHTML = '';
    if (value.length === 0) return;

    const filtered = products.filter(product =>
    product.toLowerCase().includes(value.toLowerCase())
    );

    filtered.forEach(product => {
    const div = document.createElement('div');
    div.textContent = product;
    div.onclick = () => {
    document.getElementById('searchInput').value = product;
    suggestionBox.innerHTML = '';
};
    suggestionBox.appendChild(div);
});
}
