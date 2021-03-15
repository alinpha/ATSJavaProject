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
public abstract class Base implements IBase {
    private ArrayList<IError> errors = ErrorFactory.createListInstance();
    
    
    public ArrayList<IError> getErrors() {
        return errors;
    }
    
    public void addError(IError e) {
        errors.add(e);
    }
}