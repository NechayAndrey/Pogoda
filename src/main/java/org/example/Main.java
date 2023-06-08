package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main extends TelegramLongPollingBot {

// переопредиление метода onUpdateReceived
    @Override
    public void onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
// создание объекта класса SendMessage
            SendMessage message = new SendMessage();
// оператор вывода
            if (messageText.equals("/start")) {
// проверка условий
                message.setChatId(chatId);
                message.setText("Нажмите на кнопоку");

                // Создаем клавиатуру
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                keyboardMarkup.setSelective(true);
                keyboardMarkup.setResizeKeyboard(true);
                keyboardMarkup.setOneTimeKeyboard(false);

                // Создаем кнопки
                KeyboardButton button1 = new KeyboardButton();
                button1.setText("Погода");

                // Создаем ряды кнопок
                KeyboardRow row1 = new KeyboardRow();
                row1.add(button1);

                // Добавляем ряды кнопок в клавиатуру
                keyboardMarkup.setKeyboard(List.of(row1));

                // Устанавливаем клавиатуру в сообщение
                message.setReplyMarkup(keyboardMarkup);
// обработка исключений
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            // проверка условий нажата ли кнопка
            String out;
            if (messageText.equals("Погода")){
                wither pogoda = new wither();
                out = pogoda.witherNow();
                message.setChatId(chatId);
                message.setText(out);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();

                }

            }
            //вывод в инфы в консоль
            System.out.println("id: " + update.getMessage().getChatId().toString()
                    + "\n"
                    + "massage: " + update.getMessage().getText());



        }
    }

    //переопредиление метод класса getBotUsername
    @Override
    public String getBotUsername() {
        return "bot_Pogoda_best_bot";
    }

     @Override
    public String getBotToken() {
        return "5975228854:AAG0ix5f8Lv7c2MX8XWMrfMpiN-xCltgp8k";
    }
// точка входа в программу
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}