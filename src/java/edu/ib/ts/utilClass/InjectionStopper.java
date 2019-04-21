package edu.ib.ts.utilClass;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class InjectionStopper {
    
   public String prepareString(String string){
       string.replace("'", "");
       return string;
   }
}
