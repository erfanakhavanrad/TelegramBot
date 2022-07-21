import org.checkerframework.checker.units.qual.A;
import org.glassfish.grizzly.utils.ArrayUtils;
import org.glassfish.jersey.internal.inject.ParamConverters;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.AnnotatedArrayType;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 4/26/22
 *
 * @author Erfan Akhavan
 */
public class PollingBot extends TelegramLongPollingBot {

    String url = "jdbc:sqlserver://localhost;databaseName=Telegram" + ";encrypt=true;trustServerCertificate=true", user = "tiny",
            password = "ResidentEvil6", sql, selectAllQuery;
    //           String messageForList;
    StringBuilder messageForList2, messageForList4;

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom().getFirstName());
        System.out.println(update.getMessage().getText());


//        System.out.println(update.getChatMember());
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

        } else if (command.equals("/getitemslist")) {
            String message = "wdaaaaaaaaa";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
//            response.setText(message);

            try {
                getItemList(connectToDatabase(url, user, password));
//                response.setText(String.valueOf(messageForList));
                response.setText(String.valueOf(messageForList4));

            } catch (SQLException e) {
                e.printStackTrace();
            }


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

    private Connection connectToDatabase(String url, String user, String password) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url
                    , user, password);
            System.out.println("Connected");

            // First method
//            Statement statement = connection.createStatement();
//            sql = "INSERT INTO users (userName, number) VALUES ('" + user + "'," + number + ")";
//            int rows = statement.executeUpdate(sql);

            // Second method
//            sql = "INSERT INTO users (userName, number) VALUES (?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, "Idiot");
//            preparedStatement.setInt(2, 66);
//            int rows = preparedStatement.executeUpdate();

//            if (rows > 0) System.out.println("1 Row effected.");


        } catch (SQLException e) {
            System.out.println("There was an error!");
            e.printStackTrace();
        }
        return connection;
    }


    private void getItemList(Connection connection) throws SQLException {
        selectAllQuery = "SELECT * FROM Goods";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectAllQuery);
        int count = 0;
//        messageForList ="Goods ";
        messageForList2 = new StringBuilder(" Goods list:");
//        ArrayList<Integer> listOfGoodsId = new ArrayList<>();
//        ArrayList<String> listOfGoodsName = new ArrayList<>();
//        ArrayList<Integer> listOfGoodsNumber = new ArrayList<>();
        ArrayList<Users> userArrayList = new ArrayList<>();
        while (resultSet.next()) {

//            String name = resultSet.getString("userName");
//            int number2 = resultSet.getInt("number");
//            System.out.printf("user %d: %s - %d\n", count, name, number2);
//            System.out.println("Number: " + count + ", Name: " + name + ", number: " + number2);
//            messageForList="Number: " + count + ", Name: " + name + ", number: " + number2;

//            listOfGoodsId.add(count);
//            listOfGoodsName.add(resultSet.getString("userName"));
//            listOfGoodsNumber.add(resultSet.getInt("number"));

//            messageForList.append(listOfGoodsId.indexOf(count));
//            messageForList.append(listOfGoodsName.indexOf(count));
//            messageForList.append(listOfGoodsNumber.indexOf(count));

            Users users = new Users(count, resultSet.getString("goodName"), resultSet.getInt("price"));
            userArrayList.add(users);

            String name = resultSet.getString("goodName");
            int number2 = resultSet.getInt("price");
            messageForList2.append(System.lineSeparator()+name+": " + number2);
            System.out.printf("user %d: %s - %d\n", count, name, number2);

//            messageForList2.append(userArrayList.toString());
//            messageForList2.append(System.lineSeparator()).append(userArrayList.toString());
            System.out.println(messageForList2);
            count++;

        }
messageForList4 = messageForList2;
    }


}
