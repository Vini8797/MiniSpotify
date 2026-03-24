package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class PlaylistService {

    private HashMap<Long, Playlist> playlists = new HashMap<>();
    private Long proximoId = 1L;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MusicaService musicaService;

    public Playlist cadastrarPlaylist(Playlist playlist) {
        if (playlist.getUsuario() == null || playlist.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário deve ser informado");
        }

        Usuario usuario = usuarioService.buscarUsuario(playlist.getUsuario().getId());

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        playlist.setUsuario(usuario);
        playlist.setId(proximoId++);
        playlist.setAtivo(true);

        if (playlist.getMusicas() == null) {
            playlist.setMusicas(new ArrayList<>());
        }

        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    public Collection<Playlist> listarPlaylists() {
        ArrayList<Playlist> ativas = new ArrayList<>();

        for (Playlist playlist : playlists.values()) {
            if (playlist.isAtivo()) {
                ativas.add(playlist);
            }
        }

        return ativas;
    }

    public Playlist buscarPlaylist(Long id) {
        Playlist playlist = playlists.get(id);

        if (playlist == null || !playlist.isAtivo()) {
            throw new RuntimeException("Playlist não encontrada");
        }

        return playlist;
    }

    public Playlist atualizarPlaylist(Long id, Playlist playlistAtualizada) {
        Playlist playlist = playlists.get(id);

        if (playlist == null || !playlist.isAtivo()) {
            throw new RuntimeException("Playlist não encontrada");
        }

        playlist.setNome(playlistAtualizada.getNome());
        playlist.setPublica(playlistAtualizada.isPublica());

        return playlist;
    }

    public void deletarPlaylist(Long id) {
        Playlist playlist = playlists.get(id);

        if (playlist == null || !playlist.isAtivo()) {
            throw new RuntimeException("Playlist não encontrada");
        }

        playlist.setAtivo(false);
    }

    public Playlist adicionarMusica(Long idPlaylist, Long idMusica, Long idUsuario) {
        Playlist playlist = buscarPlaylist(idPlaylist);
        Musica musica = musicaService.buscarMusica(idMusica);
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        if (!playlist.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Apenas o dono da playlist pode adicionar músicas");
        }

        for (Musica m : playlist.getMusicas()) {
            if (m.getId().equals(musica.getId())) {
                throw new RuntimeException("Essa música já está na playlist");
            }
        }

        playlist.getMusicas().add(musica);
        return playlist;
    }
}
