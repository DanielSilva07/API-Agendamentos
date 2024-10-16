package com.daniel.silva.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AgendarDTO(String nome, String email , LocalDateTime localDateTime, String diaMes,String descricao) {



}
