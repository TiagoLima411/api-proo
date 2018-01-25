/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tiago
 */
public class ConvertMap {
    public static Map converterToMap(String message) {
        Map mapCliente = new HashMap();
        mapCliente.put("message", message);        
        return mapCliente;
    }
}
