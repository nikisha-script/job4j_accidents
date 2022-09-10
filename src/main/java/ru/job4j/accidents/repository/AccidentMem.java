package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents;
    private final Map<Integer, Rule> rules;
    private final Map<Integer, AccidentType> types;
    private AtomicInteger id;

    public AccidentMem() {
        this.accidents = new HashMap<>();
        this.rules = new HashMap<>();
        this.types = new HashMap<>();
        this.id = new AtomicInteger(1);
    }

    public List<Accident> getAllAccident() {
        return accidents.values().stream().toList();
    }

    public void addAccident(Accident accident) {
        accident.setId(id.get());
        accidents.put(id.getAndIncrement(), accident);
    }

    public void updateAccident(int id, Accident accident) {
        accidents.replace(id, accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<AccidentType> getTypes() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
        return types.values().stream().toList();
    }

    public List<Rule> getRyles() {
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
        return rules.values().stream().toList();
    }
}
