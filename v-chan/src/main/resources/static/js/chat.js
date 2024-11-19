document.addEventListener("DOMContentLoaded", function () {
    let isFirstConnection = sessionStorage.getItem("isFirstConnection") !== "true";
    const socket = new WebSocket("ws://localhost:8080/ws/chat");
    const messageInput = document.getElementById("messageInput");
    const sendMessageBtn = document.getElementById("sendMessageBtn");
    const chatbox = document.getElementById("chatbox");

    // Manejar la conexión abierta
    socket.onopen = function () {
        console.log("WebSocket connection established.");
        if (isFirstConnection) {
            sessionStorage.setItem("isFirstConnection", "true");
        }
    };

    // Recibir mensajes del servidor
	socket.onmessage = function (event) {
	    const data = JSON.parse(event.data); // Convertir a JSON
	    const messageElement = document.createElement("div");

	    switch (data.type) {
	        case "system":
	            messageElement.classList.add("system-message");
	            messageElement.textContent = data.content;
	            break;
	        case "sender":
	            messageElement.classList.add("sender-message");
	            messageElement.textContent = `Tú: ${data.content}`;
	            break;
	        case "receiver":
	            messageElement.classList.add("receiver-message");
	            messageElement.textContent = `${data.sender}: ${data.content}`;
	            break;
	    }

	    chatbox.appendChild(messageElement);
	    chatbox.scrollTop = chatbox.scrollHeight; // Desplazar hacia abajo automáticamente
	};


    // Manejar la desconexión
    socket.onclose = function () {
        console.log("WebSocket connection closed.");
        if (sessionStorage.getItem("isFirstConnection") === "true") {
            sessionStorage.removeItem("isFirstConnection");
        }
    };

    // Enviar mensajes al servidor
    sendMessageBtn.addEventListener("click", function () {
        const message = messageInput.value.trim();
        if (message) {
            socket.send(message);
            messageInput.value = ""; // Limpiar el campo de texto
        }
    });

    // Manejar envío al presionar "Enter"
    messageInput.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            sendMessageBtn.click();
        }
    });

    // Cerrar el WebSocket correctamente al salir
    window.addEventListener("beforeunload", function () {
        socket.close();
    });
});

