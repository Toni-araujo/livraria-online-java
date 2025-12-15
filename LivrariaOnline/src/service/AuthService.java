// service/AuthService.java (nova ou atualizada)
package service;

import model.Cliente;
import repository.ClienteRepository;
import java.util.Optional;

public class AuthService {
    private ClienteRepository clienteRepo = new ClienteRepository();
    private Cliente clienteLogado = null;
    
    public boolean cadastrar(String cpf, String nome, String email, String senha) {
        if (clienteRepo.buscarPorCpf(cpf).isPresent()) {
            return false;
        }
        
        Cliente cliente = new Cliente(cpf, nome, email, senha);
        clienteRepo.salvar(cliente);
        return true;
    }
    
    public boolean login(String cpf, String senha) {
        Optional<Cliente> clienteOpt = clienteRepo.buscarPorCpf(cpf);
        
        if (clienteOpt.isPresent() && clienteOpt.get().validarSenha(senha)) {
            this.clienteLogado = clienteOpt.get();
            return true;
        }
        return false;
    }
    
    public boolean recuperarSenha(String cpf, String email) {
        Optional<Cliente> clienteOpt = clienteRepo.buscarPorCpf(cpf);
        
        if (clienteOpt.isPresent() && clienteOpt.get().getEmail().equals(email)) {
            System.out.println("Email de recuperação enviado para: " + email);
            return true;
        }
        return false;
    }
    
    public boolean atualizarPerfil(String novoEmail, String novoEndereco) {
        if (clienteLogado == null) return false;
        
        clienteLogado.setEmail(novoEmail);
        clienteLogado.setEndereco(novoEndereco);
        clienteRepo.salvar(clienteLogado);
        return true;
    }
    
    public void logout() {
        this.clienteLogado = null;
    }
    
    public Cliente getClienteLogado() { 
        return clienteLogado; 
    }
    
    public boolean isLogado() { 
        return clienteLogado != null; 
    }
}
