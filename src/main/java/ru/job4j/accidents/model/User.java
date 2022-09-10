package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    private String password;

    @NonNull
    private String username;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    private boolean enabled;


}
