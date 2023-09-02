package ru.ITslesarev;

public class MapTest {
    public static void main(String[] args) {
        DataLogistic dataLogistic = new DataLogistic();
        dataLogistic.fillEmployes();
        System.out.println(dataLogistic.skladMap);
    }
}
