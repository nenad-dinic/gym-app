
async function addToWishlist(userId, trainingId) {
    let response = await fetch("/wishlist/add?uId=" + userId + "&tId=" + trainingId, {
        method: "post"
    })
    window.location.reload();
}

function editTraining(id) {
    window.location.href='/training/modify?id=' + id;
}

async function addToCart(id) {
    let response = await fetch("/cart/add?id=" + id, {
        method: "post"
    })
}

const images = document.querySelectorAll('img');

images.forEach(img => {
    img.addEventListener('error', function handleError() {
        img.src = '/images/default.jpg';
    });
});

