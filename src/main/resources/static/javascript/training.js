let cart = [];
if (localStorage.getItem("cart") != undefined) {
    cart = JSON.parse(localStorage.getItem("cart"));
} else {
    cart = [];
}

let wishlist = [];
if (localStorage.getItem("wishlist") !=undefined) {
    wishlist = JSON.parse(localStorage.getItem("wishlist"));
} else {
    wishlist = [];
}

async function addToCart(id) {
    /*if (!cart.includes(id)) {
        cart.push(id);
    }
    localStorage.setItem("cart", JSON.stringify(cart));*/
    let response = await fetch("/api/cart/add?id=" + id, {
        method:"post"
    })
}

function addToWishlist(id) {
    if (!wishlist.includes(id)) {
        wishlist.push(id);
    }
    localStorage.setItem("wishlist", JSON.stringify(wishlist));
}

function editTraining(id) {
    window.location.href='/training/modify?id=' + id;
}
