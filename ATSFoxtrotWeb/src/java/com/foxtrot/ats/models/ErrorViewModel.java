/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.ats.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aline Vetrov
 */
public class ErrorViewModel implements Serializable {

    private List<String> errors = new ArrayList();

    public ErrorViewModel(String error) {
        this.errors.add(error);
    }
    
    public ErrorViewModel(){
        
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
