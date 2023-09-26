package ru.ITslesarev;
/**
 * SlesarevLogisticBot 1.0
 *
 * @author Александр Слесарев
 */

import ru.ITslesarev.model.DataLogistic;

/**
 * unloading from the database
 */
public class MapTest {
    public static void main(String[] args) {
        DataLogistic dataLogistic = new DataLogistic();
        dataLogistic.fillEmployes();
        System.out.println(dataLogistic.skladMap);
    }
}
