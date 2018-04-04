/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author recursive
 */
@Stateless
public class EnviarEmail {
    
    public void send(String body){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, body);
    }
    
}
