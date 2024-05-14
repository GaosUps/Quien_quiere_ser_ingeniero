package com.juego.qqi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
@Component
@Service
public class MyWebSocketHandler extends TextWebSocketHandler {
    private boolean estado=false;
    private static  final Logger logger = LogManager.getLogger(MyWebSocketHandler.class);
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<String, String> sessionPlayerNames = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        estado=true;
        sessions.add(session);
        String nombreJugador = sessionPlayerNames.get(session.getId());
        if (nombreJugador != null) {
            logger.info(nombreJugador + " se ha conectado al servidor"); // Log del nombre y ID del jugador
            session.sendMessage(new TextMessage("Hola, " + nombreJugador + "!"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        estado=false;
        sessions.remove(session);
        String nombreJugador = sessionPlayerNames.remove(session.getId());
        if (nombreJugador != null) {
            logger.info(nombreJugador + " se ha desconectado del servidor");
        } else {
            logger.info(session.getId() + " se ha desconectado del servidor");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String nombreJugador = message.getPayload(); // Captura el nombre del jugador
        sessionPlayerNames.put(session.getId(), nombreJugador); // Almacena el nombre con la ID de sesión
        logger.info(nombreJugador + " se ha conectado al servidor"); // Log del nombre y ID del jugador
        session.sendMessage(new TextMessage("Hola, " + nombreJugador + "!"));
        if (estado){
            enviarPregunta(session);
        }
    }

    /*
     * Función para enviar la pregunta del servidor al cliente
     *
     * */
    private void enviarPregunta(WebSocketSession session) {
        try {
            session.sendMessage(new TextMessage("¿Quién descubrió América?"));
        } catch (IOException e) {
            logger.error("Error al enviar la pregunta del servidor", e);
        }
    }
}
