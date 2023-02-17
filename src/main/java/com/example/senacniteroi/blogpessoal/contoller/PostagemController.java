package com.example.senacniteroi.blogpessoal.contoller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.senacniteroi.blogpessoal.model.Postagem;
import com.example.senacniteroi.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders =  "*")
public class PostagemController {
	
	
	
	
		@Autowired
		private PostagemRepository postagemRepository;

@GetMapping 
public ResponseEntity<List<Postagem>> getAll(){
	return ResponseEntity.ok(postagemRepository.findAll()); 
	
}

@GetMapping("{id}")
public ResponseEntity<Postagem> getById(@PathVariable Long id){
	return postagemRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

}

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}

@PostMapping
public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
	return ResponseEntity.status(HttpStatus.CREATED)
			.body(postagemRepository.save(postagem));
}




}

