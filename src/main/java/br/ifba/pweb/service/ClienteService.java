package br.ifba.pweb.service;

import br.ifba.pweb.dto.ClienteDto;
import br.ifba.pweb.model.Cliente;
import br.ifba.pweb.model.Usuario;
import br.ifba.pweb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<ClienteDto> getAllClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertEntitytoDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ClienteDto> getClienteDoUsuario(Usuario usuario, Long cliente_id) {
        Cliente cliente = clienteRepository.findByUsuarioAndId(usuario, cliente_id);
        if (cliente != null) {
            return new ResponseEntity<>(convertEntitytoDto(cliente), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<ClienteDto>> getAllClientesDoUsuario(Usuario usuario) {
        if (usuario != null) {
            return new ResponseEntity<List<ClienteDto>>(clienteRepository.findByUsuario(usuario)
                    .stream()
                    .map(this::convertEntitytoDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ClienteDto> deleteClienteDoUsuario (Usuario usuario, Long id) {
        if (usuario != null && id != null) {
            clienteRepository.deleteByUsuarioAndId(usuario, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ClienteDto> updateClienteDoUsuario (Usuario usuario, ClienteDto clienteDto) {
        if (usuario != null) {
            Cliente clienteExistente = clienteRepository.findByUsuarioAndId(usuario, clienteDto.getId());
            clienteExistente.setNome(clienteDto.getNome());
            clienteExistente.setCNPJ(clienteDto.getCnpj());
            clienteExistente.setEndereco(clienteDto.getEndereco());
            clienteRepository.save(clienteExistente);
            return new ResponseEntity<ClienteDto>(convertEntitytoDto(clienteExistente), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ClienteDto> addClienteDoUsuario (Usuario usuario, Cliente cliente) {
        if (usuario != null) {
            cliente.setUsuario(usuario);
            cliente.setDataCadastro(java.time.LocalDate.now().toString());
            clienteRepository.save(cliente);
            return new ResponseEntity<ClienteDto>(convertEntitytoDto(cliente), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ClienteDto convertEntitytoDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNome(cliente.getNome());
        clienteDto.setCnpj(cliente.getCNPJ());
        clienteDto.setEndereco(cliente.getEndereco());
        clienteDto.setDataCadastro(cliente.getDataCadastro());
        clienteDto.setUID_Usuario(cliente.getUsuario().getUID());

        return clienteDto;
    }

}
