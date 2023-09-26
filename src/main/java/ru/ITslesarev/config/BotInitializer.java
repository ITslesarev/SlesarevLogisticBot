package ru.ITslesarev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.ITslesarev.service.TelegramBot;

/**
 * SlesarevLogisticBot 1.0
 *
 * @author Александр Слесарев
 */
@Component
public class BotInitializer {
    @Autowired
    TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi((DefaultBotSession.class));
        try {
            telegramBotsApi.registerBot(bot);
        }
        catch (TelegramApiException e) {

        }
    }
}
