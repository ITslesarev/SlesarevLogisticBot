package ru.ITslesarev;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
/**
 * SlesarevLogisticBot 1.0
 *
 * @author Александр Слесарев
 */

/**
 * The code for sending messages from the main class is placed in this class
 * */
public class MyBot {
    public void sendTextMessage(String chatId, String text) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Bot bot = new Bot();

            SendMessage sendMsg = new SendMessage();
            sendMsg.setChatId(chatId);
            sendMsg.setText(text);
            bot.execute(sendMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
