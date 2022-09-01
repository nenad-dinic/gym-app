async function createLoyaltyCardRequest(id) {
    let response = await fetch("/profile/requestCard?id=" + id, {
        method: "post"
    })
}

async function block(id) {
    let response = await fetch("/profile/block?id=" + id, {
        method: "post"
    })
}

async function makeAdmin(id) {
    let response = await fetch("/profile/admin?id=" + id, {
        method: "post"
    })
}