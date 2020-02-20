package net.groot.data; 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.groot.data.Media;
import net.groot.data.MediaRepository;
import net.groot.data.MediaRequest;
import net.groot.notfound.MediaNotFoundException;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;
    
   
    public Long createNewMedia(MediaRequest mediaRequest) {
        Media media = new Media();
        media.setUniqueId(mediaRequest.getUniqueId());
        media.setCharacter(mediaRequest.getCharacter());
        media.setLocation(mediaRequest.getLocation());
        media.setThorinsCompany(mediaRequest.getThorinsCompany());
        media.setQuote(mediaRequest.getQuote());


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

        mediaToUpdate.setUniqueId(mediaToUpdateRequest.getUniqueId());
        mediaToUpdate.setCharacter(mediaToUpdateRequest.getCharacter());
        mediaToUpdate.setLocation(mediaToUpdateRequest.getLocation());
        mediaToUpdate.setThorinsCompany(mediaToUpdateRequest.getThorinsCompany());
        mediaToUpdate.setQuote(mediaToUpdateRequest.getQuote());


        return mediaToUpdate;
    }

    public void deleteMediaById(Long id) {
        mediaRepository.deleteById(id);
    }
}
