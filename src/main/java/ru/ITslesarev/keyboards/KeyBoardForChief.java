package ru.ITslesarev.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardForChief {
    public SendMessage drawKeyBoardForChief (Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<InlineKeyboardButton>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Сообщить о собрании");
        inlineKeyboardButton1.setCallbackData("СОБРАНИЕНАЧА");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Сообщить о необходимости задержаться на 30 минут");
        inlineKeyboardButton2.setCallbackData("ЗАДЕРЖКАНАЧА");
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Скоро здесь будет новая функциональность");
        inlineKeyboardButton3.setCallbackData("КНОПКА3ОТНАЧАЛЬНИКА");
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Скоро здесь будет новая функциональность");
        inlineKeyboardButton4.setCallbackData("КНОПКА4ОТНАЧАЛЬНИКА");
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);
        rowInline2.add(inlineKeyboardButton3);
        rowInline2.add(inlineKeyboardButton4);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
