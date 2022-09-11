package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentHibernate;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    public List<Accident> getAllAccident() {
        return store.getAllAccident();
    }

    public void addAccident(Accident accident) {
        store.addAccident(accident);
    }

    public void updateAccident(Accident accident) {
        store.updateAccident(accident);
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public List<Rule> getRyles() {
        return store.getRules();
    }

    public Optional<AccidentType> findTypeById(int id) {
        return store.findTypeById(id);
    }

    public Optional<Rule> findRuleById(int id) {
        return store.findRuleById(id);
    }

}
