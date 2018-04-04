/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.recebimento;

import edu.ifpb.dac.entity.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 * @author recursive
 */

//recebe
@MessageDriven(mappedName = "java:global/jms/Compras",
        activationConfig ={
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "compras")
            
        })

public class CardCredit implements MessageListener{
    
    private EnviarConfimacao ec;
    
    @Override
    public void onMessage(Message message) {
        try {
            Pedido p = message.getBody(Pedido.class);
            Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Pedido recebido pelo cartão de credito");
            confirmacao(p);
        } catch (JMSException ex) {
            Logger.getLogger(CardCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void confirmacao(Pedido pedido){
        ec.enviar(pedido);
    }
    
}
