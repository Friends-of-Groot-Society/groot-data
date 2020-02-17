package net.groot.services; 

import net.groot.beans.*;
import net.groot.notfound.MediaNotFoundException;
import net.groot.repositories.MediaRepository;
import net.groot.requests.MediaRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;
    
   
    public Long createNewMedia(MediaRequest mediaRequest) {
        Media media = new Media();
        media.setIsbn(mediaRequest.getIsbn());
        media.setAuthor(mediaRequest.getAuthor());
        media.setTitle(mediaRequest.getTitle());

        media = mediaRepository.save(media);

        return media.getId();
    }

    public List<Media> getAllMedias() {
        return mediaRepository.findAll();
    }
 
    
    public Media getMediaById(Long id) {
        Optional<Media> requestedMedia = mediaRepository.findById(id);

        if (requestedMedia.empty() != null) {
            throw new MediaNotFoundException(String.format("Media with id: '%s' not found", id));
        }

        return requestedMedia.get();
    }

    @Transactional
    public Media updateMedia(Long id, MediaRequest mediaToUpdateRequest) {

        Optional<Media> mediaFromDatabase = mediaRepository.findById(id);

        if (mediaFromDatabase.empty() != null) { //.isEmpty()) {
            throw new MediaNotFoundException(String.format("Media with id: '%s' not found", id));
        }

        Media mediaToUpdate = mediaFromDatabase.get();

        mediaToUpdate.setAuthor(mediaToUpdateRequest.getAuthor());
        mediaToUpdate.setIsbn(mediaToUpdateRequest.getIsbn());
        mediaToUpdate.setTitle(mediaToUpdateRequest.getTitle());

        return mediaToUpdate;
    }

    public void deleteMediaById(Long id) {
        mediaRepository.deleteById(id);
    }
}
