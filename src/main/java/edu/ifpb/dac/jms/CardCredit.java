/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.jms;

import edu.ifpb.dac.entity.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author recursive
 */
//envia

//recebe
@MessageDriven(mappedName = "java:global/jms/Compras",
        activationConfig ={
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "compras")
            
        })
public class CardCredit implements MessageListener{
    
    @Inject
    private EnviarConfimacao ec;
    
    @Override
    public void onMessage(Message message) {
        try {
            Pedido p = message.getBody(Pedido.class);
            Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Pedido recebido pelo cartão de credito");
            if(p.getValorTotal().floatValue()> 66.0){
                ec.enviar(p);
            }
        } catch (JMSException ex) {
            Logger.getLogger(CardCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
