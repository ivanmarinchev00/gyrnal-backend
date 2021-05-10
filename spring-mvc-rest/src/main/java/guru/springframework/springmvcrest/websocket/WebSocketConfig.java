package guru.springframework.springmvcrest.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final static String NOTIFICATION_ENDPOINT = "/notification";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getChatWebSocketHandler(), NOTIFICATION_ENDPOINT)
    .setAllowedOrigins("http://localhost:3000");
    }
    @Bean
    public WebSocketHandler getChatWebSocketHandler(){
        return new NotificationWebSocketHandler();
    }
}