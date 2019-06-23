package br.edu.utfpr.tsi.redes2email.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 10, max = 11)
    private String number;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    private Contact contact;

    public static Phone newPhone() {
        return new Phone();
    }
}
