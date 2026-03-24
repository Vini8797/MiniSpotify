package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class MusicaService {

    private HashMap<Long, Musica> musicas = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UsuarioService usuarioService;

    public Musica cadastrarMusica(Musica musica) {
        if(musica.getAlbum() == null || musica.getAlbum().getId() == null) {
            throw new RuntimeException("Álbum deve ser informado");
        }

        Album album = albumService.buscarAlbum(musica.getAlbum().getId());

        if (album == null) {
            throw new RuntimeException("Álbum não encontrado");
        }

        musica.setAlbum(album);
        musica.setArtista(album.getArtista());
        musica.setId(proximoId++);
        musica.setTotalReproducoes(0L);
        musica.setAtivo(true);

        musicas.put(musica.getId(), musica);
        return musica;
    }

    public Collection<Musica> listarMusicas() {
        ArrayList<Musica> musicasAtivas = new ArrayList<>();

        for (Musica musica : musicas.values()) {
            if (musica.isAtivo()) {
                musicasAtivas.add(musica);
            }
        }

        return musicasAtivas;
    }

    public Musica buscarMusica(Long id) {
        Musica musica = musicas.get(id);

        if (musica == null || !musica.isAtivo()) {
            throw new RuntimeException("Música não encontrada");
        }

        return musica;
    }

    public Musica atualizarMusica(Long id, Musica musicaAtualizada) {
        Musica musica = musicas.get(id);

        if (musica == null || !musica.isAtivo()) {
            throw new RuntimeException("Música não encontrada");
        }

        musica.setTitulo(musicaAtualizada.getTitulo());
        musica.setDuracaoSegundos(musicaAtualizada.getDuracaoSegundos());
        musica.setNumeroFaixa(musicaAtualizada.getNumeroFaixa());

        if (musicaAtualizada.getAlbum() != null && musicaAtualizada.getAlbum().getId() != null) {
            Album album = albumService.buscarAlbum(musicaAtualizada.getAlbum().getId());

            if (album == null) {
                throw new RuntimeException("Álbum não encontrado");
            }

            musica.setAlbum(album);
            musica.setArtista(album.getArtista());
        }

        return musica;
    }

    public void deletarMusica(Long id) {
        Musica musica = musicas.get(id);

        if (musica == null || !musica.isAtivo()) {
            throw new RuntimeException("Música não encontrada");
        }

        musica.setAtivo(false);
    }

    public Musica reproduzirMusica(Long idMusica, Long idUsuario) {
        Musica musica = buscarMusica(idMusica);
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);
        return musica;
    }

    //top10
    public List<Musica> top10MusicasMaisReproduzidas() {
        List<Musica> musicasAtivas = new ArrayList<>();

        for (Musica musica : musicas.values()) {
            if (musica.isAtivo()) {
                musicasAtivas.add(musica);
            }
        }

        musicasAtivas.sort((m1, m2) -> m2.getTotalReproducoes().compareTo(m1.getTotalReproducoes()));

        if (musicasAtivas.size() > 10) {
            return musicasAtivas.subList(0, 10);
        }

        return musicasAtivas;
    }
}

