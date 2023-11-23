/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class DataUtil {
    public static Date stringToDate(String dataString){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
        } catch (ParseException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
