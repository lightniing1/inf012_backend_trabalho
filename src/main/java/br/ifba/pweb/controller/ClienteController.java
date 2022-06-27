package br.ifba.pweb.controller;

import br.ifba.pweb.dto.ClienteDto;
import br.ifba.pweb.model.Cliente;
import br.ifba.pweb.model.Usuario;
import br.ifba.pweb.repository.ClienteRepository;
import br.ifba.pweb.repository.UsuarioRepository;
import br.ifba.pweb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarTodosClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("{uid}")
    public ResponseEntity<List<ClienteDto>> listarTodosClientesDoUsuario(@PathVariable String uid){
        Usuario usuario = usuarioRepository.findByUID(uid);
        return clienteService.getAllClientesDoUsuario(usuario);
    }

    @DeleteMapping("{uid}/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> excluirClienteDeUsuario(@PathVariable String uid, @PathVariable Long id){
        Usuario usuario = usuarioRepository.findByUID(uid);
        return clienteService.deleteClienteDoUsuario(usuario, id);
    }

    @PostMapping("{uid}")
    public ResponseEntity<ClienteDto> adicionarClienteDeUsuario(@PathVariable String uid, @RequestBody Cliente novoCliente){
        Usuario usuario = usuarioRepository.findByUID(uid);
        return clienteService.addClienteDoUsuario(usuario, novoCliente);
    }

    @PutMapping("{uid}/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> atualizaClienteDeUsuario(@PathVariable String uid, @RequestBody ClienteDto cliente) {
        Usuario usuario = usuarioRepository.findByUID(uid);
        return clienteService.updateClienteDoUsuario(usuario, cliente);
    }

    //@PutMapping

    //@DeleteMapping

}
