package br.ifba.pweb.controller;

import br.ifba.pweb.dto.ChamadoDto;
import br.ifba.pweb.model.Chamado;
import br.ifba.pweb.model.Cliente;
import br.ifba.pweb.model.Usuario;
import br.ifba.pweb.repository.ClienteRepository;
import br.ifba.pweb.repository.UsuarioRepository;
import br.ifba.pweb.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping("/chamado")
public class ChamadoController {

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

    @GetMapping("{uid}/{cliente_id}/{chamado_id}")
    public ResponseEntity<ChamadoDto> listaChamadoEspecifico(@PathVariable String uid,
                                                             @PathVariable Long cliente_id,
                                                             @PathVariable Long chamado_id) {
        Usuario usuario = usuarioRepository.findByUID(uid);
        Cliente cliente = clienteRepository.findByUsuarioAndId(usuario, cliente_id);
        return chamadoService.getChamadoEspecificoDoCliente(cliente, chamado_id);
    }

    @PostMapping("{uid}/{id}")
    public ResponseEntity<ChamadoDto> cadastraChamadoDoCliente(@PathVariable String uid,
                                                               @RequestBody Chamado chamado,
                                                               @PathVariable Long id) {

        //Usuario usuario = usuarioRepository.findByUID(uid);
        Cliente cliente = clienteRepository.findById(id).get();
        return chamadoService.addChamadoCliente(cliente, chamado);
    }

    @PutMapping("{uid}/{cliente_id}/{chamado_id}")
    @Transactional
    public ResponseEntity<ChamadoDto> atualizadoChamadoCliente(@PathVariable String uid,
                                                               @RequestBody ChamadoDto chamado,
                                                               @PathVariable Long cliente_id,
                                                               @PathVariable Long chamado_id) {
        //Cliente cliente = clienteRepository.
        Usuario usuario = usuarioRepository.findByUID(uid);
        Cliente cliente = clienteRepository.findByUsuarioAndId(usuario, cliente_id);
        return chamadoService.updateChamadoDoClienteDeUsuario(cliente, chamado);
    }

    @DeleteMapping("{uid}/{cliente_id}/{chamado_id}")
    @Transactional
    public ResponseEntity<ChamadoDto> excluiChamadoCliente(@PathVariable String uid,
                                                           @PathVariable Long cliente_id,
                                                           @PathVariable Long chamado_id) {
        Usuario usuario = usuarioRepository.findByUID(uid);
        Cliente cliente = clienteRepository.findByUsuarioAndId(usuario, cliente_id);
        return chamadoService.deleteChamadoCliente(cliente, chamado_id);
    }


}
