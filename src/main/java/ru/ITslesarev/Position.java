package ru.ITslesarev;

import java.util.HashMap;

public enum Position {
    STOREKEEPER ("Кладовщик"),
    SLOT ("Карщик/слотчик"),
    MANAGER ("Руководитель Сектора"),
    CHIEF ("Начальник отдела");
    private String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
