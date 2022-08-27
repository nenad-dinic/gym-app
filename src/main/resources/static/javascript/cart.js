async function addToCart(id) {
    let response = await fetch("/cart/add?id=" + id, {
        method: "post"
    })
}

async function removeFromCart(id) {
    let response = await fetch("/cart/remove?id=" + id, {
        method: "post"
    })
    if (response.status === 200) {
        window.location.reload();
    }
}