package net.groot.data; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GrootService {

    @Autowired
    private GrootRepository grootRepository;
    
   
    public Long createNewGroot(GrootRequest grootRequest) {
        Groot groot = new Groot();
        groot.setIsbn(grootRequest.getIsbn());
        groot.setAuthor(grootRequest.getAuthor());
        groot.setTitle(grootRequest.getTitle());
        groot.setName(grootRequest.getName());
        groot.setType(grootRequest.getType());

        groot = grootRepository.save(groot);

        return groot.getId();
    }

    public List<Groot> getAllGroots() {
        return grootRepository.findAll();
    }
 
    
    public Groot getGrootById(Long id) {
        Optional<Groot> requestedGroot = grootRepository.findById(id);

        if (requestedGroot.empty() != null) {
            throw new GrootNotFoundException(String.format("Groot with id: '%s' not found", id));
        }

        return requestedGroot.get();
    }

    @Transactional
    public Groot updateGroot(Long id, GrootRequest grootToUpdateRequest) {

        Optional<Groot> grootFromDatabase = grootRepository.findById(id);

        if (grootFromDatabase.empty() != null) { //.isEmpty()) {
            throw new GrootNotFoundException(String.format("Groot with id: '%s' not found", id));
        }

        Groot grootToUpdate = grootFromDatabase.get();

        grootToUpdate.setAuthor(grootToUpdateRequest.getAuthor());
        grootToUpdate.setIsbn(grootToUpdateRequest.getIsbn());
        grootToUpdate.setTitle(grootToUpdateRequest.getTitle());
        grootToUpdate.setName(grootToUpdateRequest.getName());
        grootToUpdate.setType(grootToUpdateRequest.getType());

        return grootToUpdate;
    }

    public void deleteGrootById(Long id) {
        grootRepository.deleteById(id);
    }
}
