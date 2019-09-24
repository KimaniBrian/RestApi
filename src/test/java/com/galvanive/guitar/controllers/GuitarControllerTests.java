package com.galvanive.guitar.controllers;


import com.galvanive.guitar.entities.Guitar;
import com.galvanive.guitar.repositories.GuitarRepositories;
import com.galvanive.guitar.services.GuitarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuitarControllerTests {
    @Autowired
    GuitarRepositories guitarRepositories;

    @Autowired
    MockMvc mvc;
    @Autowired
    GuitarService guitarService;
    private List<Guitar> guitars = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        for (int i = 0; i<10; i++){
            guitars.add(new Guitar("Brand"+i, "Model"+i, 6));
        }
    }

    @Test
    public void  getAllGuitars() throws Exception {

        mvc.perform(get("/guitars/all"))
            .andExpect(status().isOk());

     }


     @Test
    public void createGuitar() throws Exception {
        mvc.perform(post("/guitars").contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"BRAND\", \"model\" : \"MODEL\", \"strings\": \"6\" }"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("guitarId").exists());
     }
}
