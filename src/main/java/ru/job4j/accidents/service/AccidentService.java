package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public List<Accident> getAllAccident() {
        return store.getAllAccident();
    }

    public void addAccident(Accident accident) {
        store.addAccident(accident);
    }

    public void updateAccident(int id, Accident accident) {
        store.updateAccident(id, accident);
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public List<Rule> getRyles() {
        return store.getRyles();
    }

}
