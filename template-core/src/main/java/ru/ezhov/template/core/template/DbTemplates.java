package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbTemplates implements Templates {

    private Source<DataSource> source;

    public DbTemplates(Source<DataSource> source) {
        this.source = source;
    }

    @Override
    public List<Template> all() throws Exception {
        List<Template> templates = new ArrayList<>();
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareCall("SELECT ID FROM T_TEMPLATE")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery();) {
                        if (resultSet.next()) {
                            templates.add(new DbTemplate(resultSet.getInt(1), source));
                            return templates;
                        } else {
                            throw new RuntimeException("Нет такой записи");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
