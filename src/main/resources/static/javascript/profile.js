async function createLoyaltyCardRequest(id) {
    let response = await fetch("/profile/requestCard?id=" + id, {
        method: "post"
    })
}