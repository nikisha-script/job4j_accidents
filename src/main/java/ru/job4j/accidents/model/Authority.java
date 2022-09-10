package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    private String authority;
}
