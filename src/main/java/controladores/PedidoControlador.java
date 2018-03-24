/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Repositorys.PedidoRepository;
import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Pedido;
import edu.ifpb.dac.Produto;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import recebimento.CardCredit;

/**
 *
 * @author recursive
 */
@JMSDestinationDefinition(
        name = "java:global/jms/Compras",
        interfaceName = "javax.jms.Topic",
        resourceAdapter = "jmsra",
        destinationName = "compras")
@Named
@SessionScoped
public class PedidoControlador implements Serializable{
    
    private Pedido pedido;
    @Inject
    private PedidoRepository pr;
    private Cliente cliente;
    
    @Resource(lookup = "java:global/jms/Compras")
    private Topic topic;
    @Inject
    private JMSContext context;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public JMSContext getContext() {
        return context;
    }

    public void setContext(JMSContext context) {
        this.context = context;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoRepository getPr() {
        return pr;
    }

    public void setPr(PedidoRepository pr) {
        this.pr = pr;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String addProduto(Produto pro){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "AdicionaProdutoCarrinho");
        pedido.add(pro);
        return "home.xhtml";
    }
    
    public String removeProduto(Produto pro){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "RemoverProdutoCarrinho");
        pedido.remove(pro);
        return "home.xhtml";
    }
    
    public String finalizarPedido(){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "FinalizarPeido");
        pedido.setCliente(this.cliente);
        pr.add(this.pedido);
        JMSProducer createProducer = context.createProducer();
        createProducer.setProperty("compras", pedido.getId()).send(topic, this.pedido);
        return "home.xhtml";
    }
    
    @PostConstruct
    public void init(){
        this.pedido = new Pedido();
    }
}
