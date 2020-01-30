package com.monitorgps.service.sendtracklog.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFormat {
    public static String formatoFecha(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA);
        String fecha = null;
        try {
            if(date!=null) {
                fecha = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return fecha;
    }
}
