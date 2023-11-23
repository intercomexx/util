/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Sisco
 */
public class NumberUtil {
    public static Double stringDecimalParaDouble(String stringDecimal){
        if(stringDecimal != null)
            return Double.parseDouble(stringDecimal.replace(".", "").replace(",", "."));
        else
            return null;
    }
}
