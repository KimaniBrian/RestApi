package com.galvanive.guitar.controllers;
import com.galvanive.guitar.entities.Guitar;
import com.galvanive.guitar.services.GuitarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
@RequestMapping("/guitars")
public class GuitarController {

    @Autowired
    GuitarService guitarService;

    @GetMapping("/all")

    public List<Guitar> getAllGuitars(){
        return guitarService.getAll();
    }

    @PostMapping
    public Guitar createGuitar(@RequestBody Guitar guitar)   {
        return guitarService.createGuitar(guitar);
    }

    @PutMapping("/{id}")
    public  Guitar updateGuitar(@RequestBody Guitar guitar, @PathVariable Long id){
        //Get the guitar that they want to update

        Guitar g = guitarService.getGuitarById(guitar.getGuitarid());

        // If the ids match, the update will be done. Can also be done at service.uodate() method

        if (guitar != null){
            // Call the update on guitar service
            return  guitarService.updateGuitar(guitar);
        }
        else {
            return null;
        }
    }
    @PatchMapping("/{id}")
    public  Guitar patchGuitar(@PathVariable Long id, @RequestBody Guitar changes){
        Guitar g = guitarService.getGuitarById(id);

        // StringUtils.isNotEmpty comes from apache commoms-lang library

        if (StringUtils.isNotEmpty(changes.getBrand()))
            g.setBrand(changes.getBrand());
        if (StringUtils.isNotEmpty(changes.getModel()))
            g.setModel(changes.getModel());
        if (changes.getStrings()>0)
            g.setStrings(changes.getStrings());

        return guitarService.updateGuitar(g);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGuitar(@PathVariable long id){
        if (guitarService.deleteGuitar(id))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.valueOf(500));
    }
}
