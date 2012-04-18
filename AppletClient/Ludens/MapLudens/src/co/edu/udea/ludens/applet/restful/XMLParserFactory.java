/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ludens.applet.restful;

import co.edu.udea.ludens.enums.EnumDataType;

/**
 *
 * @author juanga
 */
public class XMLParserFactory {

    public static XMLParser getInstance(EnumDataType dataType) {

        if (EnumDataType.FACTOR == dataType) {
            return new XStreamParser();

        } else if (EnumDataType.MATERIAL == dataType) {
            return new XStreamParser();

        } else if (EnumDataType.POPULATION == dataType) {
            return new XStreamParser();

        }else if (EnumDataType.NOTIFICATIONS == dataType) {
            return new XStreamParser();
        }




        return null;

    }
}
