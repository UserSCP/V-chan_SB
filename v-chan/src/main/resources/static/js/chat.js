document.addEventListener("DOMContentLoaded", function() {
    const socket = new WebSocket('ws://localhost:8080/ws/chat');
    const messageInput = document.getElementById("messageInput");
    const sendMessageBtn = document.getElementById("sendMessageBtn");
    const chatbox = document.getElementById("chatbox");

    socket.onmessage = function(event) {
        const message = event.data;
        const messageElement = document.createElement("div");
        messageElement.textContent = message; 
        chatbox.appendChild(messageElement);
    };

    sendMessageBtn.addEventListener("click", function() {
        const message = messageInput.value.trim();
        if (message) {
            console.log("Sending message: " + message);  // Log para verificar el mensaje antes de enviarlo
            socket.send(message); // Enviar el mensaje al servidor
            messageInput.value = ""; // Limpiar el campo de texto
        }
    });

    messageInput.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            sendMessageBtn.click();
        }
    });
});

