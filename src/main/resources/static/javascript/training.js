
function addToWishlist(id) {

}

function editTraining(id) {
    window.location.href='/training/modify?id=' + id;
}

async function addToCart(id) {
    let response = await fetch("/cart/add?id=" + id, {
        method: "post"
    })
}
