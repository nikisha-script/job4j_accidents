package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements Crud {

    private final SessionFactory sf;

    public Accident addAccident(Accident accident) {
        run(session -> session.saveOrUpdate(accident), sf);
        return accident;
    }

    public List<Accident> getAllAccident() {
        return query("from a Accident a join fetch a.type join fetch a.rules", Accident.class, sf);
    }

    public List<Rule> getRules() {
        return query("from r Rule r", Rule.class, sf);
    }

    public List<AccidentType> getTypes() {
        return query("from t Type t", AccidentType.class, sf);
    }

    public Optional<Accident> findById(int id) {
        return optional(
                "from a Accident a join fetch a.type join fetch a.rules where a.id = :fId", Accident.class,
                Map.of("fId", id),
                sf
        );
    }

    public Optional<AccidentType> findTypeById(int id) {
        return optional(
                "from a AccidentType a where a.id = :fId", AccidentType.class,
                Map.of("fId", id),
                sf
        );
    }

    public Optional<Rule> findRuleById(int id) {
        return optional(
                "from r Rule r where r.id = :fId", Rule.class,
                Map.of("fId", id),
                sf
        );
    }

    public void updateAccident(Accident accident) {
        run(session -> session.saveOrUpdate(accident), sf);
    }

}
