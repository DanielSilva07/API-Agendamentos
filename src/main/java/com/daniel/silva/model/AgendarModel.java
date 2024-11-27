package com.daniel.silva.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Agendamentos")
public class AgendarModel {

    @Id
    private String id;

    @NotBlank
    private String nome;

    private String descricao;

    @NotBlank
    private String data;

    private String email;

    private LocalDateTime localDateTime ;


}
