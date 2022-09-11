package ru.job4j.accidents.repository.dataspring;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accidents.model.Rule;

public interface RuleRepo extends JpaRepository<Rule, Integer> {
}
