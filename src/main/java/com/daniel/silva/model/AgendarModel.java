package com.daniel.silva.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Setter
@Getter
@Document(collection = "Agendamentos")
public class AgendarModel {

    @Id
    private String id;

    @NotBlank
    private String nome;

    private String descricao;

    @NotBlank
    private String diaMes;

    private String email;

    private LocalDateTime localDateTime ;



    public AgendarModel(){

    }

    public AgendarModel(String id, String nome, String email, LocalDateTime localDateTime , String diaMes,String descricao) {
        this.id = id;
        this.diaMes= diaMes;
        this.nome = nome;
        this.descricao=descricao;
        this.email = email;
        this.localDateTime = localDateTime;
    }


}
