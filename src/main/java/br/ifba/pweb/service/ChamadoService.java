package br.ifba.pweb.service;

import br.ifba.pweb.dto.ChamadoDto;
import br.ifba.pweb.model.Chamado;
import br.ifba.pweb.model.Cliente;
import br.ifba.pweb.model.Usuario;
import br.ifba.pweb.repository.ChamadoRepository;
import br.ifba.pweb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    public ResponseEntity<ChamadoDto> addChamadoCliente(Cliente cliente, Chamado chamado) {
        if (cliente != null) {
            chamado.setCliente(cliente);
            chamado.setDataCadastro(java.time.LocalDate.now().toString());
            chamadoRepository.save(chamado);
            return new ResponseEntity<ChamadoDto>(convertEntitytoDto(chamado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<ChamadoDto>> getAllChamados() {
        return new ResponseEntity<List<ChamadoDto>>(chamadoRepository.findAll()
                .stream()
                .map(this::convertEntitytoDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<ChamadoDto> getChamadoEspecificoDoCliente(Cliente cliente, Long chamadoId) {
        Chamado chamadoExistente = chamadoRepository.findByClienteAndId(cliente, chamadoId);
        if (chamadoExistente != null) {
            return new ResponseEntity<ChamadoDto>(convertEntitytoDto(chamadoExistente), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<ChamadoDto>> getAllChamadosDoClienteDeUsuario(Usuario usuario) {
        //Acha todos os cliente do usuario
        List<Cliente> clientes = new ArrayList<>(clienteRepository.findByUsuario(usuario));
        List<ChamadoDto> chamados = new ArrayList<>();

        for (Cliente cliente : clientes) {
            chamados.addAll(chamadoRepository.findAllByCliente(cliente)
                    .stream()
                    .map(this::convertEntitytoDto).toList());
        }

        return new ResponseEntity<List<ChamadoDto>>(chamados, HttpStatus.OK);

    }

    public ResponseEntity<ChamadoDto> deleteChamadoCliente(Cliente cliente, Long id) {
        Chamado chamado = chamadoRepository.findByClienteAndId(cliente, id);
        if (chamado != null) {
            chamadoRepository.deleteByClienteAndId(cliente, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ChamadoDto> updateChamadoDoClienteDeUsuario(Cliente cliente, ChamadoDto chamadoDto) {
        Chamado chamado = chamadoRepository.findByClienteAndId(cliente, chamadoDto.getId());
        if (cliente != null || chamado != null) {
            chamado.setAssunto(chamadoDto.getAssunto());
            chamado.setComplemento(chamadoDto.getComplemento());
            chamado.setStatus(chamadoDto.getStatus());
            chamadoRepository.save(chamado);
            return new ResponseEntity<ChamadoDto>(convertEntitytoDto(chamado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ChamadoDto convertEntitytoDto(Chamado chamado) {
        ChamadoDto chamadoDto = new ChamadoDto();
        chamadoDto.setId(chamado.getId());
        chamadoDto.setCliente_id(chamado.getCliente().getId());
        chamadoDto.setAssunto(chamado.getAssunto());
        chamadoDto.setStatus(chamado.getStatus());
        chamadoDto.setComplemento(chamado.getComplemento());
        chamadoDto.setNome_cliente(chamado.getCliente().getNome());
        chamadoDto.setCadastro(chamado.getDataCadastro());

        return chamadoDto;
    }

}
