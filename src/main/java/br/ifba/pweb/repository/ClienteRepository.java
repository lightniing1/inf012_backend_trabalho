package br.ifba.pweb.repository;

import br.ifba.pweb.model.Cliente;
import br.ifba.pweb.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    List<Cliente> findByUsuario(Usuario usuario);
    void deleteByUsuarioAndId(Usuario usuario, Long Id);
    Cliente findByUsuarioAndId(Usuario usuario, Long id);

    List<Cliente> findAllByUsuario(Usuario usuario);

}
