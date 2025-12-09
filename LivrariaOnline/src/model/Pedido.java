package model; // ATUALIZADO EM 09/12/2025

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String clienteCpf;
    private Map<Livro, Integer> itens;
    private double total;
    private LocalDateTime dataHora;
    private String tipo; // "COMPRA" ou "ALUGUEL"

    public Pedido() {}
    
    // CONSTRUTOR CORRIGIDO: Aceita Map<Livro, Integer>
    public Pedido(String clienteCpf, Map<Livro, Integer> itens, String tipo) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.clienteCpf = clienteCpf;
        this.itens = itens;
        this.tipo = tipo;
        this.dataHora = LocalDateTime.now();
        calcularTotal();
    }
    
    private void calcularTotal() {
        total = 0;
        if (itens != null) {
            for (Map.Entry<Livro, Integer> entry : itens.entrySet()) {
                total += entry.getKey().getPreco() * entry.getValue();
            }
        }
    }
    
    // Getters
    public String getId() { return id; }
    public String getClienteCpf() { return clienteCpf; }
    public Map<Livro, Integer> getItens() { return itens; }
    public double getTotal() { return total; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getTipo() { return tipo; }
    
    @Override
    public String toString() {
        return String.format("Pedido %s | cliente=%s | total=R$ %.2f | itens=%d | %s",
            id, clienteCpf, total, itens.size(), dataHora.toString());
    }
}
