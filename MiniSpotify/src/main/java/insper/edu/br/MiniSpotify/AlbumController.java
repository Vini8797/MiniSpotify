package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/albuns")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @PostMapping
    public Album criarAlbum(@RequestBody Album album) { return albumService.cadastrarAlbum(album);}

    @GetMapping
    public Collection<Album> listarAlbuns() { return albumService.listarAlbuns();}

    @GetMapping("/{id}")
    public Album buscarAlbum(@PathVariable Long id) { return albumService.buscarAlbum(id);}

    @PutMapping("/{id}")
    public Album atualizarAlbum(@PathVariable Long id, @RequestBody Album album) { return albumService.atualizarAlbum(id, album);}

    @DeleteMapping("/{id}")
    public void deletarAlbum(@PathVariable Long id) { albumService.deletarAlbum(id);}
}
