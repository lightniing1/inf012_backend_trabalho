package br.ifba.pweb.service;

import br.ifba.pweb.dto.ChamadoDto;
import br.ifba.pweb.dto.UsuarioDto;
import br.ifba.pweb.model.Chamado;
import br.ifba.pweb.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public UsuarioDto getUsuario(Usuario usuario) {
        return convertEntitytoDto(usuario);
    }

    private UsuarioDto convertEntitytoDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getName());
        usuarioDto.setUid(usuario.getUID());
        usuarioDto.setProfile_pic(usuario.getFoto());

        return usuarioDto;
    }

}
