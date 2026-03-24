package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    public Artista criarArtista(@RequestBody Artista artista) { return artistaService.cadastrarArtista(artista);}

    @GetMapping
    public Collection<Artista> listarArtistas() { return artistaService.listarArtistas();}

    @GetMapping("/{id}")
    public Artista buscarArtista(@PathVariable Long id) { return artistaService.buscarArtista(id);  }

    @PutMapping("/{id}")
    public Artista atualizarArtista(@PathVariable Long id, @RequestBody Artista artista) { return artistaService.atualizarArtista(id,artista);}

    @DeleteMapping("/{id}")
    public void deletarArtista(@PathVariable Long id) { artistaService.deletarArtista(id);}

}
