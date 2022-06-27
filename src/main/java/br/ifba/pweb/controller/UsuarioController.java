package br.ifba.pweb.controller;

import br.ifba.pweb.model.Usuario;
import br.ifba.pweb.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Usuario> listaNomePorUid(@PathVariable String uid) {

        Usuario usuario = usuarioRepository.findByUID(uid);

        if (usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastraNovoUsuario(@RequestBody Usuario novoUsuario) {

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping("{uid}")
    @Transactional
    public ResponseEntity<Usuario> atualizaCadastroUsuario (@PathVariable String uid, @RequestBody Usuario usuario) {

        Usuario usuarioExistente = usuarioRepository.findByUID(uid);
        usuarioExistente.setName(usuario.getName());

        return new ResponseEntity<>(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
    }

}
