package io.github.gaosups.qqi.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig
	implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(final @NotNull StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(final @NotNull MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/queue");
		registry.setApplicationDestinationPrefixes("/app");
	}
}
