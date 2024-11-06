package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.domain.OwnerTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class OwnerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllOwners() throws Exception {
        this.mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").exists())
                .andDo(print());
    }


    @Test
    public void testFindOwnerByIdOK() throws Exception {
        int id = 1;

        mockMvc.perform(get("/owners/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.nombres").isNotEmpty())
                .andDo(print());
    }


    @Test
    public void testFindOwnerByIdNotFound() throws Exception {
        int id = 999;
        mockMvc.perform(get("/owners/" + id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    public void testCreateOwner() throws Exception {
        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setNombres("Paulo Garcia");
        newOwnerTO.setAddress("123 Main St");
        newOwnerTO.setTelephone("123456789");
        newOwnerTO.setEmail("john@example.com");

        mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombres", is("Paulo Garcia")))
                .andExpect(jsonPath("$.address", is("123 Main St")))
                .andExpect(jsonPath("$.telephone", is("123456789")))
                .andExpect(jsonPath("$.email", is("john@example.com")))
                .andDo(print());
    }


    @Test
    public void testUpdateOwner() throws Exception {
        int id = 1;

        OwnerTO updatedOwnerTO = new OwnerTO();
        updatedOwnerTO.setNombres("Jane Doe");
        updatedOwnerTO.setAddress("456 Elm St");
        updatedOwnerTO.setTelephone("987654321");
        updatedOwnerTO.setEmail("jane@example.com");

        mockMvc.perform(put("/owners/" + id)
                        .content(om.writeValueAsString(updatedOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres", is("Jane Doe")))
                .andExpect(jsonPath("$.address", is("456 Elm St")))
                .andExpect(jsonPath("$.telephone", is("987654321")))
                .andExpect(jsonPath("$.email", is("jane@example.com")))
                .andDo(print());
    }


    @Test
    public void testDeleteOwner() throws Exception {
        int id = 2;

        mockMvc.perform(delete("/owners/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete ID: " + id))
                .andDo(print());
    }


    @Test
    public void testDeleteOwnerNotFound() throws Exception {
        int id = 999;

        mockMvc.perform(delete("/owners/" + id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
