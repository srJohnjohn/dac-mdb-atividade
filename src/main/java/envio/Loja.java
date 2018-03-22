/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envio;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Pedido;
import edu.ifpb.dac.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 *
 * @author recursive
 */
@JMSDestinationDefinition(
        name = "java:global/jms/Compras",
        interfaceName = "javax.jms.Topic",
        resourceAdapter = "jmsra",
        destinationName = "compras")
@Stateful
@Named
public class Loja {
    
    private Pedido pedido = new Pedido();
    private List<Produto> carrinho = new ArrayList<>();
    private long cartao;
    private Cliente cliente = new Cliente("meireles", "meirelesqqq");
    private Produto produto = new Produto();

    @Resource(lookup = "java:global/jms/Compras")
    private Topic topic;
    @Inject
    private JMSContext context;
    
    public Loja(){
        
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public long getCartao() {
        return cartao;
    }

    public void setCartao(long cartao) {
        this.cartao = cartao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String adicionaproduto(Produto pro){
        Logger.getGlobal().log(Level.INFO, "produtoadicionado", "aaa");
        carrinho.add(pro);
        System.out.println(carrinho);
        return "index.xhtml";
    }

    public String removerProduto(Produto pro){
        Logger.getGlobal().log(Level.INFO, "produtoadicionado", "aaa");

        carrinho.remove(pro);
        return "index.xhtml";
    }

    public String finalizarPedido(){
        pedido.setProdutos(carrinho);
        pedido.setCliente(cliente);
        //    public void enviar(String mensagem) {
        JMSProducer createProducer = context.createProducer();
//        for (int i = 0; i < 100; i++) {
//            String enviar = mensagem + " - " + i;
        String enviar = pedido.toString();
        createProducer.setProperty("compras", pedido.getId())
                .send(topic, enviar);
        Logger.getGlobal().log(Level.INFO, "Mensagem enviada:{0}", enviar);
//        }
        return "index.xhtml";
    }

    
    
}
