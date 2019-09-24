package com.galvanive.guitar.controllers;


import com.galvanive.guitar.entities.Guitar;
import com.galvanive.guitar.services.GuitarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
