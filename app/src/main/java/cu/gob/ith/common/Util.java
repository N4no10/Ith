package cu.gob.ith.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String changeDateFormat(String fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            try {

                Date d = sdf.parse(fecha);
                sdf.applyPattern("yyyy-MM-dd");

                return sdf.format(d);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String formatDate(long date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
