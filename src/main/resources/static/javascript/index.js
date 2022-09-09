function filterTrainings() {
    let name = document.getElementById("name").value;
    let type = document.getElementById("type").value;
    let priceMin = parseInt(document.getElementById("priceMin").value);
    let priceMax = parseInt(document.getElementById("priceMax").value);
    let trainer = document.getElementById("trainer").value;
    let difficulty = document.getElementById("difficulty").value;
    let group = document.getElementById("group").value;
    let rows = document.getElementById("trainingTable").getElementsByTagName("tr");

    for (let i = 1; i < rows.length; i++) {
        let nameC = rows[i].getElementsByTagName("td")[1];
        let typeC = rows[i].getElementsByTagName("td")[2];
        let priceC = rows[i].getElementsByTagName("td")[3];
        let trainerC = rows[i].getElementsByTagName("td")[4];
        let groupC = rows[i].getElementsByTagName("td")[5];
        let difficultyC = rows[i].getElementsByTagName("td")[6];

        if (!nameC?.innerText.toLowerCase().includes(name.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        } if (!typeC?.innerText.toLowerCase().includes(type.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        } if (!(parseInt(priceC?.innerText) >= (isNaN(priceMin) ? -Infinity:priceMin) && parseInt(priceC.innerText) <= (isNaN(priceMax) ? Infinity:priceMax))) {
            rows[i].style.display = "none";
            continue;
        } if (!trainerC?.innerText.toLowerCase().includes(trainer.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        } if (!groupC?.innerText.toLowerCase().includes(group.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        } if (!difficultyC?.innerText.toLowerCase().includes(difficulty.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        }
        rows[i].style.display = "";

    }
}

const images = document.querySelectorAll('img');

images.forEach(img => {
    img.addEventListener('error', function handleError() {
        img.src = '/images/default.jpg';
    });
});
