package insper.edu.br.MiniSpotify;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class UsuarioService {

    private HashMap<Long,Usuario> usuarios = new HashMap<>();
    private Long proximoId = 1L;

    public Usuario cadastrarUsuario(Usuario usuario) {
        usuario.setId(proximoId++);
        usuarios.put(usuario.getId(),usuario);
        return usuario;
    }

    public Collection<Usuario> listarUsuarios() {
        return usuarios.values();
    }

    public Usuario buscarUsuario(Long id) {
        return usuarios.get(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarios.get(id);

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setTipoPlano(usuarioAtualizado.getTipoPlano());

        return usuario;
    }

    public void deletarUsuario(Long id){
        Usuario usuario = usuarios.get(id);

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        usuario.setAtivo(false);
    }
}
