package ru.ITslesarev;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.ITslesarev.keyboards.*;

import java.util.List;

/**
 * SlesarevLogisticBot 1.0
 *
 * @author Александр Слесарев
 */

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            MyBot myBot = new MyBot();
            String chat_id = message.getChatId().toString();
            switch (message.getText()) {
                case "/start": {
                    StartKeyboard startKeyboard = new StartKeyboard();
                    SendMessage sendMsg = startKeyboard.sendStartMessage(message, "Здравствуйте, на какой должности вы трудитесь?");
                    try {
                        execute(sendMsg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/help": {
                    myBot.sendTextMessage(chat_id, "/help помощь\n/start выбрать должность\n/settings настройки\n/problem сообщить о проблеме");
                    break;
                    /**
                     * This will be implemented in the following versions
                     */
                }
                case "/settings": {
                    myBot.sendTextMessage(chat_id, "Cкоро здесь будет новая функциональность");
                    break;
                }
                case "/problem": {
                    {
                        DataLogistic dataLogistic = new DataLogistic();
                        dataLogistic.fillEmployes();
                        String post = DataLogistic.skladMap.get(message.getChatId().toString());
                        /**
                         * checking the presence of a user in the database
                         * */
                        if (post == null) {
                            myBot.sendTextMessage(chat_id, "Сначала выберите должность для начала работы с ботом");
                        }
                        /**
                         * drawing the keyboard for different positions
                         * */
                        else {
                            switch (post) {
                                case "кладовщик": {
                                    KeyBoardForStorekeeper keyBoardForStorekeeper = new KeyBoardForStorekeeper();
                                    SendMessage sendMsg = keyBoardForStorekeeper.drawKeyBoardForStorekeeper(message, "Cообщите о проблеме");
                                    try {
                                        execute(sendMsg);
                                    } catch (TelegramApiException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                case "Карщик/слотчик": {
                                    KeyBoardForSlot keyBoardForSlot = new KeyBoardForSlot();
                                    SendMessage sendMsg = keyBoardForSlot.drawKeyBoardForSlot(message, "Сообщите о проблеме");
                                    try {
                                        execute(sendMsg);
                                    } catch (TelegramApiException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                case "Руководитель сектора": {
                                    KeyBoardForManager keyBoardForManager = new KeyBoardForManager();
                                    SendMessage sendMsg = keyBoardForManager.drawKeyBoardForManager(message, "Сообщите о проблеме");
                                    try {
                                        execute(sendMsg);
                                    } catch (TelegramApiException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                case "Начальник отдела": {

                                    KeyBoardForChief keyBoardForChief = new KeyBoardForChief();
                                    SendMessage sendMsg = keyBoardForChief.drawKeyBoardForChief(message, "Сообщите о проблеме");
                                    try {
                                        execute(sendMsg);
                                    } catch (TelegramApiException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                default:
                    myBot.sendTextMessage(chat_id, "Воспользуйтесь командами из списка:\n/help помощь\n/start выбрать должность\n/settings настройки\n/problem сообщить о проблеме");
            }
            /**
             *listening to callbacks
             * */
        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
            if (call_data.equals("КЛАДОВЩИК")) {
                DataLogistic dataLogistic = new DataLogistic();
                dataLogistic.fillEmployes();
                String post = DataLogistic.skladMap.get(chat_id);
                if (post == null) {
                    dataLogistic.connectToData("кладовщик", chat_id);
                    try {
                        execute(new SendMessage(chat_id, "Вы кладовщик"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    /**
                     * Re-selecting a position
                     * */
                } else if (post != null) {
                    try {
                        execute(new SendMessage(chat_id, "Нельзя повторно сохранить должность в базу данных. Обратитесь к начальнику отдела"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("КАРЩИКСЛОТЧИК")) {
                DataLogistic dataLogistic = new DataLogistic();
                dataLogistic.fillEmployes();
                String post = DataLogistic.skladMap.get(chat_id);
                if (post == null) {
                    dataLogistic.connectToData("Карщик/слотчик", chat_id);
                    try {
                        execute(new SendMessage(chat_id, "Вы Карщик/слотчик"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (post != null) {
                    try {
                        execute(new SendMessage(chat_id, "Нельзя повторно сохранить должность в базу данных. Обратитесь к начальнику отдела"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("РУКОВОДИТЕЛЬСЕКТОРА")) {
                DataLogistic dataLogistic = new DataLogistic();
                dataLogistic.fillEmployes();
                String post = DataLogistic.skladMap.get(chat_id);
                if (post == null) {
                    dataLogistic.connectToData("Руководитель сектора", chat_id);
                    try {
                        execute(new SendMessage(chat_id, "Вы руководитель сектора"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (post != null) {
                    try {
                        execute(new SendMessage(chat_id, "Нельзя повторно сохранить должность в базу данных. Обратитесь к начальнику отдела"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("НАЧАЛЬНИКОТДЕЛА")) {
                DataLogistic dataLogistic = new DataLogistic();
                dataLogistic.fillEmployes();
                String post = DataLogistic.skladMap.get(chat_id);
                if (post == null) {
                    dataLogistic.connectToData("Начальник отдела", chat_id);
                    try {
                        execute(new SendMessage(chat_id, "Вы начальник отдела"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (post != null) {
                    try {
                        execute(new SendMessage(chat_id, "Нельзя повторно сохранить должность в базу данных. Обратитесь к начальнику отдела"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("ПРОПУСК")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("Карщик/слотчик");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "У вас пропуск. Пополните товар"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("ПРИНТЕР")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("Руководитель сектора", "Начальник отдела");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Требуется починка принтера"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("ПОДДОНБАФФЕР")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("Руководитель сектора", "Начальник отдела");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Заваленный поддон"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("АЛКОБЛОК")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("Руководитель сектора", "Начальник отдела");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Блокировка алкоголя"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("ПЕРЕСОРТ")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("Руководитель сектора", "Начальник отдела");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Пересорт на слоту"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("КНОПКА3ОТСЛОТЧИКА")) {

            } else if (call_data.equals("КНОПКА4ОТСЛОТЧИКА")) {

            } else if (call_data.equals("СОБРАНИЕ")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("кладовщик");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Собрание в 00:45"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("ЗАДЕРЖКА")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("кладовщик", "Карщик/слотчик");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Необходимо задержаться, чтобы собрать весь объём"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                /**
                 * This will be implemented in the following versions
                 */
            } else if (call_data.equals("КНОПКА3ОТРУКОВОДИТЕЛЯ")) {
                /**
                 * This will be implemented in the following versions
                 */
            } else if (call_data.equals("КНОПКА4ОТРУКОВОДИТЕЛЯ")) {

            } else if (call_data.equals("СОБРАНИЕНАЧА")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("кладовщик", "Карщик/слотчик");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Собрание в 00:45"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("ЗАДЕРЖКАНАЧА")) {
                DataLogistic dataLogistic = new DataLogistic();
                List<String> list = dataLogistic.callFor("кладовщик", "Карщик/слотчик");
                for (String str : list) {
                    try {
                        execute(new SendMessage(str, "Необходимо задержаться, чтобы собрать весь объём"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                /**
                 * This will be implemented in the following versions
                 */
            } else if (call_data.equals("КНОПКА3ОТНАЧАЛЬНИКА")) {
                /**
                 * This will be implemented in the following versions
                 */
            } else if (call_data.equals("КНОПКА4ОТНАЧАЛЬНИКА")) {

            }
        }
    }

    public String getBotUsername() {
        return System.getenv("TELEGRAM_BOT_NAME");
    }

    public String getBotToken() {
        return System.getenv("TELEGRAM_BOT_TOKEN");
    }
}

