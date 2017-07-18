package wire;

/**
 * Created by crypt on 5/21/2016.
 */
public enum User {

    FINANCE("testUser3", "testUser3"),
    BACKOFFICE("testUser2", "testUser2"),
    ORDER_MANAGER("testUser1", "testUser1"),
    NUMCOM_USER("testUser1", "testUser1"),
    PARTNER_USER("partnerUser1", "partnerUser1"),
    INVALID_USER("invalidUser", "invalidUser"),

    /**/;
    private String username;
    private String password;


    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
