package ru.ITslesarev;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class DataLogistic {
    public static HashMap<String, String> skladMap = new HashMap<>();
    public void connectToData(String position, String number_id) {
        fillEmployes();
        if (!(skladMap.containsKey(number_id)))
        {
            String url = "jdbc:mysql://localhost:3306/logistic";
        String userName = getMySQLUserName();
        String password = getMySQLPassword();
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            String update = "INSERT INTO users (post, chat_id) VALUES (\'" + position + "\',\'" + number_id + "\')";
            int resultSet = statement.executeUpdate(update);
            statement.close();
            connection.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
    }else {
            System.out.println("Нельзя повторно сохранить должность в базу данных. Обратитесь к начальнику отдела");
        }
    }
    public void fillEmployes (){
        String url = "jdbc:mysql://localhost:3306/logistic";
        String userName = getMySQLUserName();
        String password = getMySQLPassword();
        try{
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT post, chat_id FROM users";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String id = resultSet.getString("chat_id");
                String position = resultSet.getString("post");
                skladMap.put(id, position);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    public List<String> callFor(String position){
        DataLogistic dataLogistic =  new DataLogistic();
        dataLogistic.fillEmployes();
        List <String> listPosition = new ArrayList<>();
        for (Map.Entry<String,String> entry : dataLogistic.skladMap.entrySet()){
            if(position.equals(entry.getValue())){
                listPosition.add(entry.getKey());
            }
        }
        return listPosition;
    }
    public List<String> callFor(String position, String position2){
        DataLogistic dataLogistic = new DataLogistic();
        dataLogistic.fillEmployes();
        List <String> listPosition = new ArrayList<>();
        for (Map.Entry<String,String> entry : dataLogistic.skladMap.entrySet()){
            if (position.equals(entry.getValue())){
                listPosition.add(entry.getKey());
            }else if(position2.equals(entry.getValue())){
                listPosition.add(entry.getKey());
            }
        }return listPosition;
    }

    public void drawKeybordForPosition () {

    }

    public String getMySQLUserName () {
        return System.getenv("MYSQL_USERNAME");
    }
    public String getMySQLPassword () {
        return System.getenv("MYSQL_PASSWORD");
    }
}
