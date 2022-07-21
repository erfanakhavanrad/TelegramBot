import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * 4/26/22
 *
 * @author Erfan Akhavan
 */
public class PollingBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getFrom().getFirstName());

        System.out.println(update.getChatMember());
        String command = update.getMessage().getText();
        if (command.equals("/run")) {
            String message = "Keep Moving forward till you eviscerate your enemies!";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "PollingJavaBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5378198272:AAFxcutJlYdeP3ckkNz3ew49g_T4vUOXEns";
    }

}
