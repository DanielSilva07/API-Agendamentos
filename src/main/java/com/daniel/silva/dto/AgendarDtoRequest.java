package com.daniel.silva.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AgendarDtoRequest(@NotBlank String nome, @NotBlank String email , LocalDateTime localDateTime,
                                @NotBlank String data, @NotBlank String descricao) {
}
