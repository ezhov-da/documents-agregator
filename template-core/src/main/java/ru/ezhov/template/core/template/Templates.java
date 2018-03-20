package ru.ezhov.template.core.template;

import ru.ezhov.template.core.Name;

import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Templates {
    List<Template> all();

    Template newTemplate(String name, Name tableName, String username);

    Template template(int idTemplate);

    //TODO: обновление шаблона
    //TODO: удаление шаблона
}
