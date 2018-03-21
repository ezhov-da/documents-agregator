package ru.ezhov.template.core.template;

import ru.ezhov.template.core.name.Name;

import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Templates {
    List<Document> all();

    Document newTemplate(String name, Name tableName, String username);

    Document template(int idTemplate);

    //TODO: обновление шаблона
    //TODO: удаление шаблона
}
