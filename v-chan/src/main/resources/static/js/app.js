function showModal() {
    var modal = document.getElementById('imageModal');
    var countdown = document.getElementById('countdown');
    var modalMessage = document.getElementById('modalMessage');
    
    // Mostrar el modal
    modal.style.display = 'flex';

    // Inicializar la cuenta regresiva
    var timeLeft = 2;  // 2 segundos

    var countdownInterval = setInterval(function() {
        countdown.textContent = timeLeft;
        timeLeft--;

        if (timeLeft < 0) {
            clearInterval(countdownInterval);
            modalMessage.textContent = 'Imagen guardada con éxito';  // Cambiar el mensaje
            // Recargar la página después de 1 segundo
            setTimeout(function() {
                window.location.reload();
            }, 1000);
        }
    }, 1000);
}
