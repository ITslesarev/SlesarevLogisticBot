package ru.ITslesarev;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        DataLogistic dataLogistic =  new DataLogistic();
        dataLogistic.fillEmployes();
        String str = "Карщик/слотчик";
        for (Map.Entry<String,String> entry : dataLogistic.skladMap.entrySet()){
            if(str.equals(entry.getValue())){
                System.out.println(entry.getKey());
            }
        }
    }
}
