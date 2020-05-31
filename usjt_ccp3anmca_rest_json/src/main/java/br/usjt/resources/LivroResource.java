import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.model.beans.Livro;
import br.usjt.model.repository.Ropository.LivroRepository;

@RestController
@RequestMapping ("/livros")
public class LivroResource {
@Autowired
private LivroRepository livroRepo;
@GetMapping ("/lista")
public List <Livro> todosOsLivros (){
return livroRepo.findAll();
}

@PostMapping ("/salvar")
@ResponseStatus (HttpStatus.CREATED)
public void salvar ( @RequestBody Livro livro, HttpServletResponse response) {
Livro l = livroRepo.save(livro);
URI uri = ServletUriComponentsBuilder.
fromCurrentServletMapping().path("/{id}").
buildAndExpand(l.getId()).
toUri();
response.setHeader("Location", uri.toASCIIString());
}

@GetMapping ("/{id}")
public Livro buscarPeloId (@PathVariable Long id) {
return livroRepo.getOne(id);
}

}