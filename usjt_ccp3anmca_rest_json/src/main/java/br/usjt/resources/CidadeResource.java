import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.model.beans.Cidade;
import br.usjt.model.repository.CidadeRepository;


public class CidadeResource{
	@Autowired
	private CidadeRepository cidadeRepo;

	@GetMapping("/listar")
	public List<Cidade> listarTodos() {
		return cidadeRepo.findAll();
	}

	@GetMapping("/listarByLetra")
	public List<Cidade> listarCidadeLetra(@RequestParam String letra) {
		return cidadeRepo.findByCidadeStartsWith(letra);
	}

	@GetMapping("/listarByLatitudeLongitude")
	public Cidade listarCidade(@RequestParam Double latitude, @RequestParam Double longitude) {
		return cidadeRepo.findOneByLatitudeAndLongitude(latitude, longitude);
	}

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
		cidade = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(cidade.getId()).toUri();

		return ResponseEntity.created(uri).body(cidade);
	}
}