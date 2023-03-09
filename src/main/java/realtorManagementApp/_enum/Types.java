package realtorManagementApp._enum;

import java.nio.charset.StandardCharsets;

public enum Types {
    FLAT(new String("Квартира".getBytes(), StandardCharsets.UTF_8)),
    COTTAGE(new String("Коттедж".getBytes(), StandardCharsets.UTF_8)),
    ROOM(new String("Комната".getBytes(), StandardCharsets.UTF_8)),
    HOUSE(new String("Дом".getBytes(), StandardCharsets.UTF_8));

    private String title;

    Types(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
