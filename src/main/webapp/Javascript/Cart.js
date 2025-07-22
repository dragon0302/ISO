function aggiornaQuantita(ID,Quantity,prezzo){
    const newQuantity = Quantity.value;

    if (typeof prezzo !== 'number' || isNaN(prezzo)) {
        console.error('Prezzo non valido');
        return;
    }

    fetch("/ISO_16_war_exploded/Updatecarrello", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },

        body: "productID="+ ID + "&quantity="+ newQuantity + "&p=" + prezzo
    })
        .then(response => response.json())
        .then(date => {
            console.log("risposta del server: ", date)
            document.getElementById('prezzo-totale').innerHTML = date["prezzototale"];
            const productBar = Quantity.closest('.product-bar');

            if (newQuantity === '0') {
                productBar.remove()
            }
        })
        .catch(error => console.error('Errore: ', error))
}