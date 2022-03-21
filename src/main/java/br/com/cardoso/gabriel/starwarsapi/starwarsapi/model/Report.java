package br.com.cardoso.gabriel.starwarsapi.starwarsapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Rebel.class)
    private Rebel accuser;

    @ManyToOne(targetEntity = Rebel.class)
    private Rebel accused;

}
