package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    public Collection<Usuario> listarUsuario() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscarUsuario(id);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id,usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public void deletarUsario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}
