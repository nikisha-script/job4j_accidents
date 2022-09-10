package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents;
    private AtomicInteger id;

    public AccidentMem() {
        this.accidents = new HashMap<>();
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
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        return types;
    }

    public List<Rule> getRyles() {
        List<Rule> rules = new ArrayList<>();
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
        return rules;
    }
}
