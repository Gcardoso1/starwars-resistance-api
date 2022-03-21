package br.com.cardoso.gabriel.starwarsapi.starwarsapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RebelResource {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ResourcesEnum resource;

    @ManyToOne(targetEntity = Rebel.class)
    @JsonBackReference
    private Rebel rebel;

    private Integer quantity;
}
