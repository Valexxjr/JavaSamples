import java.util.*;

public class AppLocale {
    private static final String strMsg = "Myloc";
    private static Locale loc = Locale.getDefault();
    private static ResourceBundle res =
            ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );

    static Locale get() {
        return AppLocale.loc;
    }

    static void set( Locale loc ) {
        AppLocale.loc = loc;
        res = ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );
    }

    static ResourceBundle getBundle() {
        return AppLocale.res;
    }

    static String getString( String key ) {
        return AppLocale.res.getString(key);
    }

    public static final String customername="name1";
    public static final String constructorname="name2";
    public static final String error="err";
    public static final String customer="customer";
    public static final String constructor="constructor";
    public static final String cash="cashmess";
    public static final String cashsign="sign";
    public static final String subordinate="sub";
    public static final String projectcost="projcost";
    public static final String workcost="workcost";
    public static final String subordlot="sublot";
    public static final String creation="create";
}
