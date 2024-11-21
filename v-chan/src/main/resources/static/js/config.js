// Función para mostrar el div par correspondiente
function showSection(event) {
    const currentId = event.target.id;  // Obtener el ID del div clickeado
    const nextSectionId = `section-${parseInt(currentId.split('-')[1]) + 1}`;  // Generar el ID del siguiente div par

    // Ocultar todos los divs de la columna derecha
    const allSections = document.querySelectorAll('.right-column .container-config-item');
    allSections.forEach((section) => section.setAttribute('hidden', true));

    // Mostrar el div par correspondiente
    const nextSection = document.getElementById(nextSectionId);
    if (nextSection) {
        nextSection.removeAttribute('hidden');
    }
}

// Añadir el evento de clic a los div impares
document.getElementById('section-1').addEventListener('click', showSection);
document.getElementById('section-3').addEventListener('click', showSection);
document.getElementById('section-5').addEventListener('click', showSection);
document.getElementById('section-7').addEventListener('click', showSection);


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