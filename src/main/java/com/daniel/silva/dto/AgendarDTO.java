package com.daniel.silva.dto;

import java.time.LocalDateTime;

public record AgendarDTO(String nome, String email , LocalDateTime localDateTime, String data,String descricao) {
}
