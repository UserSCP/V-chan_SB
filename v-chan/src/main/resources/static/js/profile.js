const span = document.querySelector('.shadow-icon');
const configContainer = document.querySelector('.config-container');
const configButton = document.getElementById('configButton');
configButton.addEventListener('click', () => {
	configContainer.classList.toggle('expanded');
});
span.addEventListener('click', () => {
    span.classList.toggle('active');
});






