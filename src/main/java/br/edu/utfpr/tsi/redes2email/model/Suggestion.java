package br.edu.utfpr.tsi.redes2email.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    private Contact contact;

}
