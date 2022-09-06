async function removeFromReservation(scheduleId, reservationId) {
    let response = await fetch("/reservation/schedule?sId=" + scheduleId + "&rId=" + reservationId, {
        method: "post"
    })
    window.location.reload();

}