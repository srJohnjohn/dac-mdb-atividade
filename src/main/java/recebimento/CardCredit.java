/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recebimento;

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
import javax.jms.Topic;

/**
 *
 * @author recursive
 */
//envia
@JMSDestinationDefinition(
        name = "java:global/jms/Conficamacao",
        interfaceName = "javax.jms.Topic",
        resourceAdapter = "jmsra",
        destinationName = "confirmacao")
//recebe
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
public class CardCredit implements MessageListener{

    @Resource(lookup = "java:global/jms/Topic")
    private Topic topic;
    @Inject
    private JMSContext context;
    
    @Override
    public void onMessage(Message message) {
        try {
            String body = message.getBody(String.class);
            Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Mensagem recebida:{0} no sms", body);
            confirmacao(body);
        } catch (JMSException ex) {
            Logger.getLogger(CardCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void confirmacao(String pedido){
        JMSProducer createProducer = context.createProducer();
        String enviar = pedido;
        createProducer.setProperty("categoria", pedido)
                .send(topic, enviar);
        Logger.getGlobal().log(Level.INFO, "Mensagem enviada:{0}", enviar);
    }
    
}
