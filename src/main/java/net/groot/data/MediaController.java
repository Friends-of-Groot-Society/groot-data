package net.groot.data; 



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

import net.groot.data.Media;
import net.groot.data.MediaRequest;
import net.groot.data.MediaService;

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

