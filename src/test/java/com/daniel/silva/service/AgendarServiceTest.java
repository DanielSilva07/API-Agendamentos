package com.daniel.silva.service;

import com.daniel.silva.domain.model.AgendarModel;
import com.daniel.silva.dto.AgendarDtoRequest;
import com.daniel.silva.fixtures.Fixtures;
import com.daniel.silva.repository.AgendarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AgendarServiceTest {

    @InjectMocks
    private AgendarService agendarService;

    @Mock
    private AgendarRepository agendarRepository;

    @Test
     void deveSalvarAgendamentoComSucesso() {
       var request = Fixtures.buildAgendarDtoRequest();
        var agendarModel = AgendarModel.builder().build();

        when(agendarRepository.save(any(AgendarModel.class)))
                .thenReturn(agendarModel);

        var resultado = agendarService.save(request);

        assertNotNull(resultado);
        verify(agendarRepository, times(1)).save(any(AgendarModel.class));
    }


    @Test
    void deveAtualizarAgendamentoComSucesso() {
        Long id = 1L;
        AgendarDtoRequest request = new AgendarDtoRequest(
                "Nome atualizado",
                "novo.email@exemplo.com",
                "31/10/2025",
                "Descrição atualizada"
        );
        when(agendarRepository.existsById(id)).thenReturn(true);
        when(agendarRepository.save(any(AgendarModel.class))).thenAnswer(invocation ->
                invocation.getArgument(0));

        ResponseEntity<AgendarModel> response = agendarService.update(id, request);

        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals(request.nome(), response.getBody().getNome());
        assertEquals(request.email(), response.getBody().getEmail());
        assertEquals(request.data(), response.getBody().getData());
        assertEquals(request.descricao(), response.getBody().getDescricao());

        verify(agendarRepository, times(1)).existsById(id);
        verify(agendarRepository, times(1)).save(any(AgendarModel.class));
    }



}
