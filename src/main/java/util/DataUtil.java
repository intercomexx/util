/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.slf4j.LoggerFactory;
import util.controller.Controller;

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
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DataUtil.class);
    public static Date stringToDate(String dataString){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
        } catch (ParseException ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
        }
        return null;
    }
}
