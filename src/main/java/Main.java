import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.*;

/**
 * 4/26/22
 *
 * @author Erfan Akhavan
 */
public class Main {
    public static void main(String[] args) {
        String url, user, password, sql;
        int number = 42;
        url = "jdbc:sqlserver://localhost;databaseName=Telegram" + ";encrypt=true;trustServerCertificate=true";
        user = "tiny";
        password = "ResidentEvil6";

        try {
            Connection connection = DriverManager.getConnection(url
                    , user, password);
            System.out.println("Connected");

            // First method
//            Statement statement = connection.createStatement();
//            sql = "INSERT INTO users (userName, number) VALUES ('" + user + "'," + number + ")";
//            int rows = statement.executeUpdate(sql);

            // Second method
            sql = "INSERT INTO users (userName, number) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Idiot");
            preparedStatement.setInt(2, 66);
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) System.out.println("1 Row effected.");
            connection.close();

        } catch (SQLException e) {
            System.out.println("There was an error!");
            e.printStackTrace();
        }

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new PollingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
