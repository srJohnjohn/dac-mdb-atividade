/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.jms;

import edu.ifpb.dac.entity.Pedido;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 *
 * @author recursive
 */
//envia
@JMSDestinationDefinition(
        name = "java:global/jms/Confirmacao",
        interfaceName = "javax.jms.Queue",
        resourceAdapter = "jmsra",
        destinationName = "confirmada")
@Stateless
public class EnviarConfimacao implements Serializable{
    
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:global/jms/Confirmacao")
    private Queue fila;

    public JMSContext getContext() {
        return context;
    }

    public void setContext(JMSContext context) {
        this.context = context;
    }

    public Queue getFila() {
        return fila;
    }

    public void setFila(Queue fila) {
        this.fila = fila;
    }
    
    public void enviar(Pedido p){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Cortão de credito enviado confirmacao");
        JMSProducer createProducer = context.createProducer();
        createProducer.send(fila, p);
        
    }
    
}
