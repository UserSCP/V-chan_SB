function showModal() {
    var modal = document.getElementById('imageModal');
    var countdown = document.getElementById('countdown');
    var modalMessage = document.getElementById('modalMessage');
        modal.style.display = 'flex';
    var timeLeft = 1;
    var countdownInterval = setInterval(function() {
        countdown.textContent = timeLeft;
        timeLeft--;
        if (timeLeft < 0) {
            clearInterval(countdownInterval);
            modalMessage.textContent = 'Imagen guardada con éxito';
            setTimeout(function() {
                window.location.reload();
            }, 1000);
        }
    }, 1000);
}
