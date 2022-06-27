package br.ifba.pweb.controller;

import br.ifba.pweb.dto.ChamadoDto;
import br.ifba.pweb.model.Chamado;
import br.ifba.pweb.model.Cliente;
import br.ifba.pweb.model.Usuario;
import br.ifba.pweb.repository.ChamadoRepository;
import br.ifba.pweb.repository.ClienteRepository;
import br.ifba.pweb.repository.UsuarioRepository;
import br.ifba.pweb.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping("/chamado")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoService chamadoService;


    @GetMapping
    public ResponseEntity<List<ChamadoDto>> listaTodosChamados() {
        return chamadoService.getAllChamados();
    }

    @GetMapping("{uid}")
    public ResponseEntity<List<ChamadoDto>> listarTodosChamadosDosClientesDeUsuario(@PathVariable String uid){
        Usuario usuario = usuarioRepository.findByUID(uid);
        return chamadoService.getAllChamadosDoClienteDeUsuario(usuario);
    }

    @PostMapping("{uid}/{id}")
    public ResponseEntity<ChamadoDto> cadastraChamadoDoCliente(@PathVariable String uid,
                                                               @RequestBody Chamado chamado,
                                                               @PathVariable Long id) {

        //Usuario usuario = usuarioRepository.findByUID(uid);
        Cliente cliente = clienteRepository.findById(id).get();
        return chamadoService.addChamadoCliente(cliente, chamado);
    }

    //@PutMapping

    //@DeleteMapping


}
