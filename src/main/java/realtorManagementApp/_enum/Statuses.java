package realtorManagementApp._enum;

public enum Statuses {
    STATUS_POSTED("Posted"),
    STATUS_PROCESSING("Processing"),
    STATUS_SOLD("Sold");

    private String title;

    Statuses(String title) {
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
