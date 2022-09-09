package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents;
    private int id;

    public AccidentMem() {
        this.accidents = new HashMap<>();
        this.id = 1;
    }

    public List<Accident> getAllAccident() {
        return accidents.values().stream().toList();
    }

    public void addAccident(Accident accident) {
        accident.setId(id);
        accidents.put(id++, accident);
    }

    public void updateAccident(int id, Accident accident) {
        accidents.replace(id, accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
