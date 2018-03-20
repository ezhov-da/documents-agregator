package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Name;
import ru.ezhov.template.core.Source;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbTemplates implements Templates {

    private Source<DataSource> source;
    private TemplateId templateId;

    public DbTemplates(final TemplateId templateId, final Source<DataSource> source) {
        this.source = source;
        this.templateId = templateId;
    }

    @Override
    public List<Template> all() {
        List<Template> templates = new ArrayList<>();
        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("SELECT ID FROM T_TEMPLATE")) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            templates.add(new DbTemplate(resultSet.getInt(1), source));
                        }

                        if (templates.isEmpty()) {
                            return Collections.EMPTY_LIST;
                        } else {
                            return templates;

                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Template newTemplate(String name, Name tableName, String username) {
        int id = templateId.get();

        try {
            try (Connection connection = source.get().getConnection()) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(
                                     "INSERT INTO T_TEMPLATE (\n" +
                                             "ID, NAME, TABLE_NAME, USERNAME" +
                                             ") VALUES (\n" +
                                             " ?, ?, ?, ?)"
                             )) {

                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, tableName.get());
                    preparedStatement.setString(4, username);

                    preparedStatement.execute();

                    return template(id);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Template template(int idTemplate) {
        return new DbTemplate(idTemplate, source);
    }
}
