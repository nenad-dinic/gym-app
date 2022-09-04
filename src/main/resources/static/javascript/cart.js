
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

function calcActualPrice() {
    let specialDateDiscount = document.getElementById("specialDiscount");
    let actualPrice = document.getElementById("actualPrice");
    let points = document.getElementById("points");
    let fullPrice = parseInt(document.getElementById("fullPrice").innerText);
    let specialDiscount = parseInt(specialDateDiscount?.innerText.replace("%", ""));
    if (isNaN(specialDiscount)) {
        specialDiscount = 0;
    }
    points.value = Math.min(Math.max(points.value, 0), 5)
    actualPrice.innerText = fullPrice * (1 - (points.value * 0.05) - (specialDiscount / 100));

}

calcFullPrice();
calcActualPrice()