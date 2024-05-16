package io.github.gaosups.qqi.configuration;

import io.github.gaosups.qqi.service.MyWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig
	implements WebSocketConfigurer {

	private final MyWebSocketHandler myWebSocketHandler;

	@Override
	public void registerWebSocketHandlers(@NotNull WebSocketHandlerRegistry registry) {
		registry.addHandler(myWebSocketHandler, "/index");
	}
}
