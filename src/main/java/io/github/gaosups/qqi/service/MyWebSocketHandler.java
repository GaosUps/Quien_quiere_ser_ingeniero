package io.github.gaosups.qqi.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {
    private boolean estado=false;
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<String, String> sessionPlayerNames = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        estado=true;
        sessions.add(session);
        String nombreJugador = sessionPlayerNames.get(session.getId());
        if (nombreJugador != null) {
            log.info(nombreJugador + " se ha conectado al servidor"); // Log del nombre y ID del jugador
            session.sendMessage(new TextMessage("Hola, " + nombreJugador + "!"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        estado=false;
        sessions.remove(session);
        String nombreJugador = sessionPlayerNames.remove(session.getId());
        if (nombreJugador != null) {
            log.info(nombreJugador + " se ha desconectado del servidor");
        } else {
            log.info(session.getId() + " se ha desconectado del servidor");
        }
    }

    @Override
    protected void handleTextMessage(@NotNull WebSocketSession session, @NotNull TextMessage message) throws Exception {
        String nombreJugador = message.getPayload(); // Captura el nombre del jugador
        sessionPlayerNames.put(session.getId(), nombreJugador); // Almacena el nombre con la ID de sesión
        log.info(nombreJugador + " se ha conectado al servidor"); // Log del nombre y ID del jugador
        session.sendMessage(new TextMessage("Hola, " + nombreJugador + "!"));
        if (estado){
            enviarPregunta(session);
        }
    }

    /*
     * Función para enviar la pregunta del servidor al cliente
     *
     * */
    private void enviarPregunta(@NotNull WebSocketSession session) {
        try {
            session.sendMessage(new TextMessage("¿Quién descubrió América?"));
        } catch (IOException e) {
            log.error("Error al enviar la pregunta del servidor", e);
        }
    }
}
