package com.techmanage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmanage.dto.UserRequest;
import com.techmanage.model.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test void postDeveRetornar201() throws Exception {
        var req = new UserRequest("João","joao@ex.com","+55 11 90000-1111",
                LocalDate.parse("1992-12-10"),UserType.EDITOR);

        mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test void getDeveRetornar404CasoIdInexistente() throws Exception {
        mvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound());
    }
}
