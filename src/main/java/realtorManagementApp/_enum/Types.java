package realtorManagementApp._enum;

public enum Types {
    FLAT("Flat"),
    COTTAGE("Cottage"),
    ROOM("Room"),
    HOUSE("House");

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
