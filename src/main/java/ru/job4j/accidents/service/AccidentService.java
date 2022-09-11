package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.dataspring.AccidentRepositoryDataSpring;
import ru.job4j.accidents.repository.dataspring.RuleRepo;
import ru.job4j.accidents.repository.dataspring.TypeRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentRepositoryDataSpring store;
    private RuleRepo ruleRepo;
    private TypeRepo typeRepo;

    public List<Accident> getAllAccident() {
        return store.getAllAccident();
    }

    public void addAccident(Accident accident) {
        store.save(accident);
    }

    public void updateAccident(Accident accident) {
        store.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public List<AccidentType> getTypes() {
        return typeRepo.findAll();
    }

    public List<Rule> getRyles() {
        return ruleRepo.findAll();
    }

    public Optional<AccidentType> findTypeById(int id) {
        return typeRepo.findById(id);
    }

    public Optional<Rule> findRuleById(int id) {
        return ruleRepo.findById(id);
    }

}
