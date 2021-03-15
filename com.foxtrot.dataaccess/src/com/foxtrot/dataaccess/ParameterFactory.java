/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.dataaccess;

import com.foxtrot.dataaccess.IParameter.Direction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aline Vetrov
 */
public abstract class ParameterFactory {
    //createInstance
    public static IParameter createInstance() {
        return new Parameter();
    }
    
    public static IParameter creteInstance(Object value) {
        return new Parameter(value);
    }
    
    public static IParameter creteInstance(Object value, Direction dir) {
        return new Parameter(value, dir);
    }
    
    public static IParameter creteInstance(Object value, Direction dir, int type) {
        return new Parameter(value, dir, type);
    }
    public static List<IParameter> createListInstance() {
        return new ArrayList<IParameter>();
    }
}
