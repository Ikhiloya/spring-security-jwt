package icom.khiloyaimokhai.springsecurityjwt.util;

/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 */
public class Constants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";


    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String CATEGORY_EVENT = "Event";
    public static final String CATEGORY_PHOTOGRAPHY = "Photography";
    public static final String CATEGORY_MAKE_UP = "Make Up";
//    private static final String CATEGORY_EVENT = "Event";


    private Constants() {
    }
}
