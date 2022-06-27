package br.ifba.pweb.service;

import br.ifba.pweb.dto.ChamadoDto;
import br.ifba.pweb.dto.ClienteDto;
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

    private ChamadoDto convertEntitytoDto(Chamado chamado) {
        ChamadoDto chamadoDto = new ChamadoDto();
        chamadoDto.setId(chamado.getId());
        chamadoDto.setCliente_id(chamado.getCliente().getId());
        chamadoDto.setAssunto(chamado.getAssunto());
        chamadoDto.setStatus(chamado.getStatus());
        chamadoDto.setComplemento(chamado.getComplemento());

        return chamadoDto;
    }

}
