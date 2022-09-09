package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

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

}
