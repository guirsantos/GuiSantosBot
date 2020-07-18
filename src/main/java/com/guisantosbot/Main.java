package com.guisantosbot;

import com.guisantosbot.GuiSantosBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main (String []args)
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new GuiSantosBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
