let lastSortedColumn = -1;

function sortTable(column, tableId) {
    let direction = 0;
    if (lastSortedColumn == column) {
        direction = !direction;
    }
    lastSortedColumn = column;

    let rows = document.getElementById(tableId).getElementsByTagName("tr");
    let x;
    let y;

    for (let i = 1; i < rows.length - 1; i++) {
        for (let j = i + 1; j < rows.length; j++) {
            x = rows[i].getElementsByTagName("td")[column].innerText.toLowerCase();
            y = rows[j].getElementsByTagName("td")[column].innerText.toLowerCase();

            if (x < y && direction == 0) {
                rows[i].parentNode.insertBefore(rows[j], rows[i]);
            } else {
                rows[i].parentNode.insertBefore(rows[j], rows[i]);
            }
        }
    }
}