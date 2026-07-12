package com.example.dodast.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
        mappedBy = "province",
        fetch = FetchType.LAZY
    )
    private List<City> cities;

}
