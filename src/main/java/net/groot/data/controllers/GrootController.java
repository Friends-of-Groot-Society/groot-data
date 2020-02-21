package net.groot.data.controllers; 



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.groot.data.entities.Groot;
import net.groot.data.requests.GrootRequest;
import net.groot.data.services.GrootService;

@RestController
@CrossOrigin
@RequestMapping("/api/groot")
public class GrootController {

    @Autowired
    private GrootService grootService;

    @PostMapping
    public ResponseEntity<Void> createNewGroot(@Valid @RequestBody GrootRequest grootRequest, UriComponentsBuilder uriComponentsBuilder) {
        Long primaryKey = grootService.createNewGroot(grootRequest);

        UriComponents uriComponents = uriComponentsBuilder.path("/api/groot/{id}").buildAndExpand(primaryKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Groot>> getAllGroots() {
        return ResponseEntity.ok(grootService.getAllGroots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groot> getGrootById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(grootService.getGrootById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Groot> updateGroot(@PathVariable("id") Long id, @Valid @RequestBody GrootRequest grootRequest) {
        return ResponseEntity.ok(grootService.updateGroot(id, grootRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroot(@PathVariable("id") Long id) {
        grootService.deleteGrootById(id);
        return ResponseEntity.ok().build();
    }

}

