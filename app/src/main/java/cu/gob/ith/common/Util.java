package cu.gob.ith.common;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class Util {

    public static String changeDateFormat(String fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            try {

                Date d = sdf.parse(fecha);
                sdf.applyPattern("dd/MM/yyyy");

                return sdf.format(d);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String formatDateCalendarView(long date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String formatDate(long date){
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
