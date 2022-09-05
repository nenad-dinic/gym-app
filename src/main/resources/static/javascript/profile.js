async function createLoyaltyCardRequest(id) {
    let response = await fetch("/profile/requestCard?id=" + id, {
        method: "post"
    })
    window.location.reload();
}

async function block(id) {
    let response = await fetch("/profile/block?id=" + id, {
        method: "post"
    })
    window.location.reload();

}

async function makeAdmin(id) {
    let response = await fetch("/profile/admin?id=" + id, {
        method: "post"
    })
    window.location.reload();
}

async function removeFromWishlist(id) {
    let response = await fetch("/profile/wishlist?id=" + id, {
        method: "post"
    })
    window.location.reload();
}