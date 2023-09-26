package ru.ITslesarev.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * SlesarevLogisticBot 1.0
 *
 * @author Александр Слесарев
 */
@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String botToken;
}
