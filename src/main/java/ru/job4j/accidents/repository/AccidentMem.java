package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {

    Map<Integer, Accident> accidents;

    public AccidentMem() {
        this.accidents = new HashMap<>();
        accidents.put(1, new Accident("Авария один", "Легкая авария", "Ул.Пупкина 12"));
        accidents.put(2, new Accident("Авария два", "авария", "Ул.Васькина 24"));
        accidents.put(3, new Accident("Авария три", "Тяжелая авария", "Ул.Иванова 13"));
    }

    public List<Accident> getAllAccident() {
        return accidents.values().stream().toList();
    }
}
