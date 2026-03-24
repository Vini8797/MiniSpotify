package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public Playlist criarPlaylist(@RequestBody Playlist playlist) {
        return playlistService.cadastrarPlaylist(playlist);
    }

    @GetMapping
    public Collection<Playlist> listarPlaylists() {
        return playlistService.listarPlaylists();
    }

    @GetMapping("/{id}")
    public Playlist buscarPlaylist(@PathVariable Long id) {
        return playlistService.buscarPlaylist(id);
    }

    @PutMapping("/{id}")
    public Playlist atualizarPlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        return playlistService.atualizarPlaylist(id, playlist);
    }

    @DeleteMapping("/{id}")
    public void deletarPlaylist(@PathVariable Long id) {
        playlistService.deletarPlaylist(id);
    }

    @PostMapping("/{idPlaylist}/musicas/{idMusica}")
    public Playlist adicionarMusica(
            @PathVariable Long idPlaylist,
            @PathVariable Long idMusica,
            @RequestHeader("X-USER-ID") Long idUsuario) {
        return playlistService.adicionarMusica(idPlaylist, idMusica, idUsuario);
    }
}