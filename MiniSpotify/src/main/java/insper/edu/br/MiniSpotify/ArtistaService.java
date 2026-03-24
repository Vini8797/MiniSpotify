package insper.edu.br.MiniSpotify;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ArtistaService {

    private HashMap<Long,Artista> artistas = new HashMap<>();
    private Long proximoId = 1L;

    public Artista cadastrarArtista(Artista artista) {
        artista.setId(proximoId++);
        artistas.put(artista.getId(),artista);
        return artista;
    }

    public Collection<Artista> listarArtistas() {
        return artistas.values();
    }

    public Artista buscarArtista(Long id) {
        return artistas.get(id);
    }

    public Artista atualizarArtista(Long id, Artista artistaAtualizado) {
        Artista artista = artistas.get(id);

        if (artista == null) {
            throw new RuntimeException("Artista não encontrado");
        }

        artista.setNome(artistaAtualizado.getNome());
        artista.setGeneroMusical(artistaAtualizado.getGeneroMusical());
        artista.setPaisOrigem(artistaAtualizado.getPaisOrigem());

        return artista;
    }

    public void deletarArtista(Long id) {
        Artista artista = artistas.get(id);

        if (artista == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        artistas.remove(id);
    }
}

