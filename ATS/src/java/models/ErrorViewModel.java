/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris.Cusack
 */
public class ErrorViewModel implements Serializable{
   private List<String> errors = new ArrayList();

   public ErrorViewModel(){}
   
    public ErrorViewModel(String error) {
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }  
}
