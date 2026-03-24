package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @PostMapping
    public Musica criarMusica(@RequestBody Musica musica) { return musicaService.cadastrarMusica(musica);}

    @GetMapping
    public Collection<Musica> listarMusicas() {
        return musicaService.listarMusicas();
    }

    @GetMapping("/{id}")
    public Musica buscarMusica(@PathVariable Long id) {
        return musicaService.buscarMusica(id);
    }

    @PutMapping("/{id}")
    public Musica atualizarMusica(@PathVariable Long id, @RequestBody Musica musica) {
        return musicaService.atualizarMusica(id, musica);
    }

    @DeleteMapping("/{id}")
    public void deletarMusica(@PathVariable Long id) {
        musicaService.deletarMusica(id);
    }

    @PostMapping("/{id}/reproduzir")
    public Musica reproduzirMusica(@PathVariable Long id, @RequestHeader("X-USER-ID") Long idUsuario) {
        return musicaService.reproduzirMusica(id, idUsuario);
    }

    @GetMapping("/top10")
    public List<Musica> top10MusicasMaisReproduzidas() {
        return musicaService.top10MusicasMaisReproduzidas();
    }
}