package com.daniel.silva.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AgendarDtoRequest(@NotBlank String nome, String email , LocalDateTime localDateTime,
                                @NotBlank String data, @NotBlank String descricao) {
}
