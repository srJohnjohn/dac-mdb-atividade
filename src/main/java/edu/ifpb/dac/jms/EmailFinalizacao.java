/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.recebimento;

import edu.ifpb.dac.entity.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author recursive
 */
@MessageDriven(mappedName = "java:global/jms/Compras",
        activationConfig ={
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "compra")
            
        })
public class EmailFinalizacao implements MessageListener{
    
    private EnviarEmail ee;

    @Override
    public void onMessage(Message message) {
        try {
            Pedido p = message.getBody(Pedido.class);
            Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Mensagem finalizada pelo cartão");
            ee.send("Email enviado, pedido finalizado" + p.getValorTotal().toString());
        } catch (JMSException ex) {
            Logger.getLogger(CardCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
