package com.nttdata.sre.Readjustment.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nttdata.sre.Readjustment.entities.Officer;
import com.nttdata.sre.Readjustment.repositories.OfficerRepository;

@RestController
//@RequestMapping(value = "/officer")
public class OfficerResources {

	@Autowired
	private OfficerRepository officerRepository;

	@GetMapping("/officer")
	public ResponseEntity<List<Officer>> findAll() {
		List<Officer> list = officerRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/officer/{id}")
	public ResponseEntity<Officer> findById(@PathVariable Long id) {
		Officer obj = officerRepository.findById(id).get();
		return ResponseEntity.ok(obj);
	}

	@DeleteMapping(value = "/officer/{id}")
	public void deleteOfficer(@PathVariable long id) {
		officerRepository.deleteById(id);
	}

	@PostMapping("/officer")
	public ResponseEntity<Object> createOfficer(@Valid @RequestBody Officer officer) {
		Officer savedOfficer = officerRepository.save(officer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedOfficer.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
