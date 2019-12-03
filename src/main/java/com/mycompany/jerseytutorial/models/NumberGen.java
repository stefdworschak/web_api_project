/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial.models;

import java.util.Random;

/**
 *
 * @author PJMOR
 */
public class NumberGen {
    Random r = new Random();
    public NumberGen() {
        
    }
    public int randomInt(int num){
        String str = "";
        for(int i =0; i<num; i++){
            str+=r.nextInt(9);
        }
        return Integer.parseInt(str);
    }
    
        public long randomLong(int num){
        String str = "";
        for(int i =0; i<num; i++){
            str+=r.nextInt(9);
        }
        return Long.parseLong(str);
    }
    
    
}
