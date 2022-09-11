package ru.job4j.accidents.repository.dataspring;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accidents.model.AccidentType;

public interface TypeRepo extends JpaRepository<AccidentType, Integer> {


}
