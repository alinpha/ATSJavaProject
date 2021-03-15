/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;

import java.util.ArrayList;

/**
 *
 * @author Aline Vetrov
 */
public abstract class ErrorFactory {
    public static IError createInstance() {
        return new Error();
    }
    
    public static IError createInstance(int code, String desc) {
        return new Error(code,desc);
    }
    
    public static ArrayList<IError> createListInstance() {
        return new ArrayList();
    }
}