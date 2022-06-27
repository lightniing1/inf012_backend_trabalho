package br.ifba.pweb.repository;

import br.ifba.pweb.model.Chamado;
import br.ifba.pweb.model.Cliente;
import br.ifba.pweb.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository <Chamado, Long> {

    List<Chamado> findAllByCliente(Cliente cliente);

}
