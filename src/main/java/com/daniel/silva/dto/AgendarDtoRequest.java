package com.daniel.silva.dto;

import jakarta.validation.constraints.NotBlank;


public record AgendarDtoRequest(@NotBlank String nome, @NotBlank String email,
                                @NotBlank String data, @NotBlank String descricao) {
}
