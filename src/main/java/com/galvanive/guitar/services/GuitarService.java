package com.galvanive.guitar.services;
import com.galvanive.guitar.entities.Guitar;
import com.galvanive.guitar.repositories.GuitarRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GuitarService  {

    @Autowired
    GuitarRepositories guitarRepository;
    public List<Guitar> getAll() {
      return guitarRepository.findAll();
    }
    public Guitar getGuitarById(Long id){
        return guitarRepository.findById(id).get();
    }
    public Guitar createGuitar(Guitar guitar) {
        return guitarRepository.save(guitar);
    }
    public boolean deleteGuitar(Long guitarId) {
        Optional <Guitar> g = guitarRepository.findById(guitarId);
        if (g.isPresent()) {
           guitarRepository.delete( g.get()) ;
           return true;
        }
        else {
            return false;
        }
    }
    public Guitar updateGuitar (Guitar guitar){
        //will fail if the guitar does not exist
        return guitarRepository.save(guitar);
    }
}
