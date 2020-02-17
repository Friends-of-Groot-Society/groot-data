package net.groot.controllers; 



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import net.groot.beans.Media;
import net.groot.requests.MediaRequest;
import net.groot.services.MediaService;
 
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping
    public ResponseEntity<Void> createNewMedia(@Valid @RequestBody MediaRequest mediaRequest, UriComponentsBuilder uriComponentsBuilder) {
        Long primaryKey = mediaService.createNewMedia(mediaRequest);

        UriComponents uriComponents = uriComponentsBuilder.path("/api/media/{id}").buildAndExpand(primaryKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Media>> getAllMedias() {
        return ResponseEntity.ok(mediaService.getAllMedias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Media> updateMedia(@PathVariable("id") Long id, @Valid @RequestBody MediaRequest mediaRequest) {
        return ResponseEntity.ok(mediaService.updateMedia(id, mediaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable("id") Long id) {
        mediaService.deleteMediaById(id);
        return ResponseEntity.ok().build();
    }

}

