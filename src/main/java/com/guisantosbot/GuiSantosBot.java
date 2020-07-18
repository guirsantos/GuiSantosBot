package com.guisantosbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.Calendar;

public class GuiSantosBot extends TelegramLongPollingBot {

    private Boolean iniciado = false;

       public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();
        String result = "";
        SendMessage message = new SendMessage();

        if(!iniciado) {
            message.setText(retornaMensagemInicial());
            message.setChatId(update.getMessage().getChatId());
            iniciado = true;
            try
            {
                execute(message);
            }
            catch (TelegramApiException e)
            {
                e.printStackTrace();
            }
        }

        switch(command) {
            case "/meunome":
                result = update.getMessage().getFrom().getFirstName();
                break;
            case "/meusobrenome":
                result = update.getMessage().getFrom().getLastName();
                break;
            case "/meunomecompleto":
                result = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + "\ud83d\udc3b";
                break;
            default:
                break;
        }

        if(!command.equals("") && !result.equals("")) {
            message.setText(result);
            message.setChatId(update.getMessage().getChatId());
            System.out.println(result);

            try
            {
                execute(message);
               // execute(sticker);
            }
            catch (TelegramApiException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println(update.getMessage().getText().toString());
    }

    public String getBotUsername() {
        return "guisantos_bot";
    }

    public String getBotToken() {
        return "1170640502:AAEyuBikT3bhKAwthMxdaZvIjn54-b0hmP0";
    }

    public String retornaPeriodoDia()
    {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String periodo = "";

        if(timeOfDay >= 0 && timeOfDay < 12){
            periodo = "Bom Dia";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            periodo = "Bom Tarde";
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            periodo = "Bom Noite";
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            periodo = "Bom Noite";
        }

        System.out.println(periodo);
        return periodo;
    }

    public String retornaMensagemInicial()
    {
        String periodo = retornaPeriodoDia();
        String mensagem = periodo + ", seja bem vendo ao GuiSantosBot, informe uma das opções abaixo: \n";

        mensagem += "\n\u2705 1 - Consulta";
        mensagem += "\n\u2705 2 - Agendamento";
        mensagem += "\n\u2705 3 - Financeiro";
        mensagem += "\n\u2705 4 - Falar com consultor";

        return mensagem;
    }
}
