package ru.job4j.accidents.repository.dataspring;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentRepositoryDataSpring extends CrudRepository<Accident, Integer> {

    @Query("select a from Accident a join fetch a.type join fetch a.rules")
    List<Accident> getAllAccident();

}
