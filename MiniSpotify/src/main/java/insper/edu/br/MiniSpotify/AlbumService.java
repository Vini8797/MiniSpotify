package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class AlbumService {

    private HashMap<Long, Album> albuns = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private ArtistaService artistaService;

    public Album cadastrarAlbum(Album album) {
        Artista artista = artistaService.buscarArtista(album.getArtista().getId());
        if (artista == null){
            throw new RuntimeException("Artista não encontrado");
        }

        album.setArtista(artista);
        album.setId(proximoId++);
        albuns.put(album.getId(),album);

        return album;
    }

    public Collection<Album> listarAlbuns() {
        return albuns.values();
    }

    public Album buscarAlbum(Long id) {
        return albuns.get(id);
    }

    public Album atualizarAlbum(Long id, Album albumAtualizado) {
        Album album = albuns.get(id);

        if (album == null) {
            throw new RuntimeException("Album não encontrado");
        }

        album.setTitulo(albumAtualizado.getTitulo());
        album.setDataLancamento(albumAtualizado.getDataLancamento());

        if (albumAtualizado.getArtista() != null) {
            Artista artista = artistaService.buscarArtista(albumAtualizado.getArtista().getId());

            if (artista == null) {
                throw new RuntimeException("Artista não encontrado");
            }

            album.setArtista(artista);
        }

        return album;
    }

    public void  deletarAlbum(Long id) {
        Album album = albuns.get(id);

        if (album == null) {
            throw new RuntimeException("Album não encontrado");
        }

        albuns.remove(id);

    }
}
