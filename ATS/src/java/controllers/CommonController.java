/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aline Vetrov
 */
public abstract class CommonController extends HttpServlet {

    private RequestDispatcher view;
    
    public RequestDispatcher getView(){
        return view;
    }
    
    public void setView(HttpServletRequest request,String viewPath){
        view = request.getRequestDispatcher(viewPath);
    }
    
    protected boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
        
    protected int getInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
    
    protected int getInteger(HttpServletRequest request, String key) {
        try {
            return Integer.parseInt(request.getParameter(key));
        } catch (Exception e) {
            return 0;
        }
    }

    protected double getDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    protected double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.parseDouble(request.getParameter(key));
        } catch (Exception e) {
            return 0.0;
        }
    }

    protected String getValue(HttpServletRequest request, String key) {
        return request.getParameter(key);       
    }

}
