package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static boolean dateBetween(Date from, Date to, Date when){
        boolean between = true;
        if(from!=null)
            between = when.after(from);
        if(to!=null)
            between = when.before(to);
        return between;
    }
}
