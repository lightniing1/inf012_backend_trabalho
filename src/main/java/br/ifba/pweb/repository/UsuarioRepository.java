package br.ifba.pweb.repository;

import br.ifba.pweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    Usuario findByUID(String UID);

}
