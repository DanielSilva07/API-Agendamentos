package com.daniel.silva.controller;

import com.daniel.silva.domain.model.AgendarModel;
import com.daniel.silva.dto.AgendarDtoRequest;
import com.daniel.silva.dto.AgendarDtoResponse;
import com.daniel.silva.service.AgendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgendarController.class)
public class AgendarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendarService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {
        AgendarDtoRequest request = new AgendarDtoRequest(
                "Cliente Teste",
                "cliente@teste.com",
                "10/12/2025",
                "Descrição do teste"
        );

        AgendarModel agendarModel = AgendarModel.builder()
                .id(1L)
                .nome("Cliente Teste")
                .email("cliente@teste.com")
                .data("10/12/2025")
                .descricao("Descrição do teste")
                .localDateTime(LocalDateTime.now())
                .build();

        when(service.save(any(AgendarDtoRequest.class))).thenReturn(agendarModel);

        mockMvc.perform(post("/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value(request.nome()))
                .andExpect(jsonPath("$.email").value(request.email()));

        verify(service, times(1)).save(any(AgendarDtoRequest.class));
    }

    @Test
    void deveRetornarListaDeAgendamentos() throws Exception {
        List<AgendarDtoResponse> agendamentos = List.of(
                new AgendarDtoResponse(1L, "Cliente 1", "cliente1@teste.com", "01/01/2025", "Desc 1", LocalDateTime.now()),
                new AgendarDtoResponse(2L, "Cliente 2", "cliente2@teste.com", "02/01/2025", "Desc 2", LocalDateTime.now())
        );

        when(service.getAll()).thenReturn(agendamentos);

        mockMvc.perform(get("/agendamento"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].nome").value("Cliente 1"))
                .andExpect(jsonPath("$[1].nome").value("Cliente 2"));

        verify(service, times(1)).getAll();
    }

    @Test
    void deveAtualizarAgendamentoComSucesso() throws Exception {
        Long id = 1L;
        AgendarDtoRequest request = new AgendarDtoRequest(
                "Cliente Atualizado",
                "atualizado@teste.com",
                "15/12/2025",
                "Descrição Atualizada"
        );

        AgendarModel agendarModel = AgendarModel.builder()
                .id(id)
                .nome("Cliente Atualizado")
                .email("atualizado@teste.com")
                .data("15/12/2025")
                .descricao("Descrição Atualizada")
                .localDateTime(LocalDateTime.now())
                .build();

        when(service.update(eq(id), any(AgendarDtoRequest.class)))
                .thenReturn(ResponseEntity.ok(agendarModel));

        mockMvc.perform(put("/agendamento/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value(request.nome()));

        verify(service, times(1)).update(eq(id), any(AgendarDtoRequest.class));
    }

    @Test
    void deveDeletarAgendamentoComSucesso() throws Exception {
        Long id = 1L;
        when(service.deleteById(id)).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/agendamento/{id}", id))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(id);
    }

    @Test
    void deveRetornarNotFoundAoDeletarAgendamentoInexistente() throws Exception {
        Long id = 999L;
        when(service.deleteById(id)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(delete("/agendamento/{id}", id))
                .andExpect(status().isNotFound());

        verify(service, times(1)).deleteById(id);
    }
}
