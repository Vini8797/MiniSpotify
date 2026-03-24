package insper.edu.br.MiniSpotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PostMapping
    public Favorito criarFavorito(@RequestBody Favorito favorito) {
        return favoritoService.cadastrarFavorito(favorito);
    }

    @GetMapping
    public Collection<Favorito> listarFavoritos() {
        return favoritoService.listarFavoritos();
    }

    @GetMapping("/{id}")
    public Favorito buscarFavorito(@PathVariable Long id) {
        return favoritoService.buscarFavorito(id);
    }

    @DeleteMapping("/{id}")
    public void deletarFavorito(@PathVariable Long id) {
        favoritoService.deletarFavorito(id);
    }
}
