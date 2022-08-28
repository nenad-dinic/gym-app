
async function removeFromCart(id) {
    let response = await fetch("/cart/remove?id=" + id, {
        method: "post"
    })
    if (response.status === 200) {
        window.location.reload();
    }
}

function calcFullPrice() {
    let priceText = document.getElementById("fullPrice");
    let price = 0;
    let rows = document.getElementById("cartTable").getElementsByTagName("tr");
    for (let i = 1; i< rows.length; i++) {
        let priceC = rows[i].getElementsByTagName("td")[4];

        price += parseInt(priceC.innerText);
    }
    priceText.innerText = price;
}

calcFullPrice();