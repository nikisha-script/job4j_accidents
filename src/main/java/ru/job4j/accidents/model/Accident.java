package ru.job4j.accidents.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Accident {

    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String text;
    @NonNull
    private String address;

    private AccidentType type;

    private List<Rule> rules;

}
