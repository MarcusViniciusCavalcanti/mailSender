package br.edu.utfpr.tsi.redes2email.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "UX_NAME", columnList = "name", unique = true)
})
@Data public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Valid
    @OneToMany(mappedBy = "contact",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    @Valid
    @OneToOne(mappedBy = "contact",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Suggestion suggestion;

    public static Contact newContact() {
        return new Contact();
    }
}