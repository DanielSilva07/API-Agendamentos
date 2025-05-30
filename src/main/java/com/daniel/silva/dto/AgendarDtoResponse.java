package com.daniel.silva.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AgendarDtoResponse{

    String id;
    String nome;
    String descricao;
    String data;
    String email;
    LocalDateTime localDateTime;

}
