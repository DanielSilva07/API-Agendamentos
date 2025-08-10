package com.daniel.silva.fixtures;

import com.daniel.silva.dto.AgendarDtoRequest;

public class Fixtures {

    public static AgendarDtoRequest buildAgendarDtoRequest() {
        return new AgendarDtoRequest(
                "daniel",
                "exemple.com.br",
                "30/10/2025",
                "Teste"
        );
    }
}
