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


import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        for (int i = 0; i<10; i++) {
            guitars.add(new Guitar("Brand" + i, "Model" + i, 6));
        }
        guitarRepositories.saveAll(guitars);
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
     @Test
    public void updateGuitarWithPut() throws  Exception {
         //Get a guitar from the ones created in setup
         Guitar guitar = guitars.get(3);
         guitar.setModel("Mode that's something totally different");
         // Manually convert it to Json

         String json = String.format("{\"guitarId\": \"%s\", \"brand\": \"%s\", \"model\" : \"%s\", \"strings\": \"%s\" }",
                 guitar.getGuitarid(), guitar.getBrand(), guitar.getModel(), guitar.getStrings());

         mvc.perform(put("/guitars/" + guitar.getGuitarid()).contentType(MediaType.APPLICATION_JSON).content(json))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("guitraId").value(guitar.getGuitarid()))
                 .andExpect(jsonPath("model").value("Model that's something totally different"));
     }

     @Test
    public void updateGuitarWithPatch() throws  Exception {

        Guitar guitar = guitars.get(5);
        String newBrand = "Updated Brand";
        String newModel = "Updated Model";
        String jsonData = String.format("{\"model\" : \"%s\", \"brand\" : \"%s\" }",
                newModel, newBrand);

        mvc.perform(patch("/guitars/"+ guitar.getGuitarid()).contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("model").value(newModel))
                .andExpect(jsonPath("brand").value(newBrand))
                .andExpect(jsonPath("strings").value(guitar.getStrings()));

     }
     @Test
    public void deleteGuitar() throws Exception {

        // pick a guitar from the ones we created
         long id = guitars.get(8).getGuitarid();

         //perform the delete, and make sure the return status is ok (2xx)

         mvc.perform(delete("/guitars/" + id))
                 .andExpect(status().isOk());
         // make sure the guitar doesn't exist in the db

         assertFalse(guitarRepositories.findById(id).isPresent());
     }
}
