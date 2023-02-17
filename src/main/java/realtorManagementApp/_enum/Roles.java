package realtorManagementApp._enum;

public enum Roles {
    ROLE_USER("ROLE_USER"),
    ROLE_REALTOR("ROLE_REALTOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String title;

    Roles(String title) {
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
