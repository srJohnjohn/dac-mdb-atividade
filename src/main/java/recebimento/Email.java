/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recebimento;

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
                    propertyValue = "compras"),
            @ActivationConfigProperty(propertyName = "messageSelector",
                    propertyValue = "categoria='sms'")
        } )
@Stateless
public class Email implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try {
            String body = message.getBody(String.class);
            Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Mensagem recebida:{0} no sms", body);
        } catch (JMSException ex) {
            Logger.getLogger(CardCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
