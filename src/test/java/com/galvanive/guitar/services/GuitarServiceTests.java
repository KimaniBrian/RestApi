package com.galvanive.guitar.services;


import com.galvanive.guitar.entities.Guitar;
import com.galvanive.guitar.repositories.GuitarRepositories;
import org.hibernate.validator.constraints.Range;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class GuitarServiceTests {

    @Autowired
    GuitarRepositories guitarRepository;

    @Autowired
    GuitarService guitarService;

    @Test
    public void getAllGuitars(){
        List<Guitar> guitars = new ArrayList<>();
        for (int i = 0; i<10; i++){
            guitars.add(new Guitar("Brand"+i , "Model"+i, 6));
        }
        guitarRepository.saveAll(guitars);
        List <Guitar> svcGuitars = guitarService.getAll();

        for (Guitar guitar : svcGuitars){
            assertNotNull(guitar.getGuitarid());
        }
    }

    @Test
    public void getOneGuitar(){
        Guitar guitar = new Guitar("Guild", "D45BLd", 6);
        guitarRepository.save(guitar);

        Guitar newGuitar = guitarService.getGuitarById(guitar.getGuitarid());

        assertNotNull(newGuitar.getGuitarid());
    }

    @Test
    public void creteGuitar(){
        Guitar guitar = new Guitar("Guild", "D45bLD", 6);

        guitar = guitarService.createGuitar(guitar);

        assertNotNull(guitar.getGuitarid());
    }

    @Test
    public void deleteGuitar(){
        Guitar guitar = new Guitar("Guild", "D45ld", 6);
        guitarRepository.save(guitar);
        Long guitarId = guitar.getGuitarid();

        boolean deleted = guitarService.deleteGuitar(guitarId);

        assertTrue(deleted);
        Optional<Guitar> deleteGuitar = guitarRepository.findById(guitar.getGuitarid());

        assertFalse(deleteGuitar.isPresent());



    }


}
