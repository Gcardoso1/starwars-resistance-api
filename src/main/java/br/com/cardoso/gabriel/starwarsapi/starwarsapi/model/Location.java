package br.com.cardoso.gabriel.starwarsapi.starwarsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float latitude;

    private Float longitude;

    private String name;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Rebel rebel;

}
