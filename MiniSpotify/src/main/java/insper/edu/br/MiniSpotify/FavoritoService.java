package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class FavoritoService {

    private HashMap<Long, Favorito> favoritos = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MusicaService musicaService;

    public Favorito cadastrarFavorito(Favorito favorito) {
        if (favorito.getUsuario() == null || favorito.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário deve ser informado");
        }

        if (favorito.getMusica() == null || favorito.getMusica().getId() == null) {
            throw new RuntimeException("Música deve ser informada");
        }

        Usuario usuario = usuarioService.buscarUsuario(favorito.getUsuario().getId());
        Musica musica = musicaService.buscarMusica(favorito.getMusica().getId());

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (musica == null) {
            throw new RuntimeException("Música não encontrada");
        }

        for (Favorito f : favoritos.values()) {
            if (f.isAtivo()
                    && f.getUsuario().getId().equals(usuario.getId())
                    && f.getMusica().getId().equals(musica.getId())) {
                throw new RuntimeException("Essa música já foi favoritada por esse usuário");
            }
        }

        favorito.setUsuario(usuario);
        favorito.setMusica(musica);
        favorito.setId(proximoId++);
        favorito.setAtivo(true);

        favoritos.put(favorito.getId(), favorito);
        return favorito;
    }

    public Collection<Favorito> listarFavoritos() {
        ArrayList<Favorito> ativos = new ArrayList<>();

        for (Favorito favorito : favoritos.values()) {
            if (favorito.isAtivo()) {
                ativos.add(favorito);
            }
        }

        return ativos;
    }

    public Favorito buscarFavorito(Long id) {
        Favorito favorito = favoritos.get(id);

        if (favorito == null || !favorito.isAtivo()) {
            throw new RuntimeException("Favorito não encontrado");
        }

        return favorito;
    }

    public void deletarFavorito(Long id) {
        Favorito favorito = favoritos.get(id);

        if (favorito == null || !favorito.isAtivo()) {
            throw new RuntimeException("Favorito não encontrado");
        }

        favorito.setAtivo(false);
    }
}
