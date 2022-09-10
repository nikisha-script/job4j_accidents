package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

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
        init();
    }

    private void init() {
        accidents.put(id.get(), new Accident(id.getAndIncrement(), "Test", "Test", "Test"));
        accidents.put(id.get(), new Accident(id.getAndIncrement(), "Test", "Test", "Test"));
        accidents.put(id.get(), new Accident(id.getAndIncrement(), "Test", "Test", "Test"));
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
}
