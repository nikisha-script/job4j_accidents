package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident addAccident(Accident accident) {
        jdbc.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into accident(name, address, type_id) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, accident.getName());
            preparedStatement.setString(2, accident.getAddress());
            preparedStatement.setInt(3, accident.getType().getId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    accident.setId(resultSet.getInt(1));
                }
            }
            return preparedStatement;
        });
        accident.getRules().forEach(rule -> jdbc.update(
                "insert into accident_rules (accident_id, rule_id) values (?, ?)",
                accident.getId(),
                rule.getId()));
        return accident;
    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("select * from types where id = ?",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                }, id);
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject("select * from rules where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }

    public List<Accident> getAllAccident() {
        return jdbc.query("select * from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeById(rs.getInt("type_id")));
                    jdbc.query("select rule_id from accident_rules where accident_id = ?",
                            (rsN, rowN) -> findRuleById(rsN.getInt("rule_id")),
                            rs.getInt("id")).forEach(accident.getRules()::add);
                    return accident;
                });
    }

    public List<AccidentType> getTypes() {
        return jdbc.query("select * from types", (rs, row) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
        });
    }

    public List<Rule> getRules() {
        return jdbc.query("select * from rules", (rs, row) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return rule;
        });
    }

    public Accident findById(int id) {
        Accident rsl = jdbc.queryForObject("select * from accident where id = ?", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setAddress(rs.getString("address"));
            accident.setType(findTypeById(rs.getInt("type_id")));
            return accident;
        }, id);
        return rsl;
    }

    public Accident updateAccident(Accident accident) {
        jdbc.update("update accident set name = ?, address = ?, type_id = ? where id = ?",
                accident.getName(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update("delete from accident_rules where accident_id = ?",
                accident.getId());
        accident.getRules().forEach(rule -> jdbc.update(
                "insert into accident_rules (accident_id, rule_id) values (?, ?)",
                accident.getId(),
                rule.getId()));
        return accident;
    }


}
