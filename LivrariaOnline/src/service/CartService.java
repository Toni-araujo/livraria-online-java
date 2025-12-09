package service; // ATUALIZADO EM 09/12/2025

import model.Carrinho;
import model.Livro;
import repository.LivroRepository;
import java.util.Optional;

public class CartService {
    private LivroRepository livroRepo = new LivroRepository();

    public Carrinho criarCarrinho(String cpf) {
        return new Carrinho(cpf);
    }

    public boolean adicionarAoCarrinho(Carrinho carrinho, String isbn, int quantidade) {
        Optional<Livro> livroOpt = livroRepo.buscarPorIsbn(isbn);
        if (livroOpt.isEmpty()) return false;
        
        carrinho.adicionar(livroOpt.get(), quantidade);
        return true;
    }

    public boolean removerDoCarrinho(Carrinho carrinho, String isbn, int quantidade) {
        Optional<Livro> livroOpt = livroRepo.buscarPorIsbn(isbn);
        if (livroOpt.isEmpty()) return false;
        
        return carrinho.remover(livroOpt.get(), quantidade);
    }
    
    public boolean removerTodosDoCarrinho(Carrinho carrinho, String isbn) {
        Optional<Livro> livroOpt = livroRepo.buscarPorIsbn(isbn);
        if (livroOpt.isEmpty()) return false;
        
        carrinho.getItens().remove(livroOpt.get());
        return true;
    }

    public double calcularTotal(Carrinho carrinho) {
        return carrinho.calcularTotal();
    }
    
    public boolean carrinhoVazio(Carrinho carrinho) {
        return carrinho.estaVazio();
    }
}
