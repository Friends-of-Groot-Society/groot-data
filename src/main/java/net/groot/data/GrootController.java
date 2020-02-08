package net.groot.data; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
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
