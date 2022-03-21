package br.com.cardoso.gabriel.starwarsapi.starwarsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rebel {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private Integer age;

    private Gender gender;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "rebel_location",
            joinColumns = @JoinColumn(name = "rebel_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Location location;

    @OneToMany(mappedBy = "rebel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RebelResource> resources;

    @OneToMany(mappedBy = "accused", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Report> reportedList;

    private boolean betrayer;

}
