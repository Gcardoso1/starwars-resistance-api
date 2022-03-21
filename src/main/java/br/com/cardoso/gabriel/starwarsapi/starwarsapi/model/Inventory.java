package br.com.cardoso.gabriel.starwarsapi.starwarsapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter
    private boolean blocked;

    @PrePersist
    void setInitialValues(){
        setBlocked(false);
    }
}
