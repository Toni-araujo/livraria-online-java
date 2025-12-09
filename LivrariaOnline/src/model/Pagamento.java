package model; // ATUALIZADO EM 09/12/2025

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum MetodoPagamento {
        CARTAO_CREDITO, PIX, BOLETO
    }
    
    public enum StatusPagamento {
        PENDENTE, APROVADO, RECUSADO
    }
    
    private String id;
    private String pedidoId;
    private double valor;
    private MetodoPagamento metodo;
    private StatusPagamento status;
    private LocalDateTime data;
    
    public Pagamento(String pedidoId, double valor, MetodoPagamento metodo) {
        this.id = "PAY" + System.currentTimeMillis();
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.metodo = metodo;
        this.status = StatusPagamento.PENDENTE;
        this.data = LocalDateTime.now();
    }
    
    // NOVO: Método para processar pagamento
    public boolean processarPagamento(String dadosPagamento) {
        try {
            // Simula delay de processamento
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulação: 90% de chance de aprovação
        boolean aprovado = Math.random() > 0.1;
        
        this.status = aprovado ? StatusPagamento.APROVADO : StatusPagamento.RECUSADO;
        return aprovado;
    }
    
    // NOVO: Gerar comprovante
    public String gerarComprovante() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(50)).append("\n");
        sb.append("         COMPROVANTE DE PAGAMENTO         \n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Pedido: ").append(pedidoId).append("\n");
        sb.append("Valor: R$ ").append(String.format("%.2f", valor)).append("\n");
        sb.append("Método: ").append(metodo.toString()).append("\n");
        sb.append("Status: ").append(status.toString()).append("\n");
        sb.append("Data: ").append(data).append("\n");
        sb.append("=".repeat(50)).append("\n");
        return sb.toString();
    }
    
    // Getters
    public String getId() { return id; }
    public String getPedidoId() { return pedidoId; }
    public double getValor() { return valor; }
    public MetodoPagamento getMetodo() { return metodo; }
    public StatusPagamento getStatus() { return status; }
    public LocalDateTime getData() { return data; }
    
    @Override
    public String toString() {
        return String.format("Pagamento[%s] %s R$%.2f %s", 
                           id, metodo, valor, status);
    }
}
