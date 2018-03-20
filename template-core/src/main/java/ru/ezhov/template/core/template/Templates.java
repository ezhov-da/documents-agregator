package ru.ezhov.template.core.template;

import java.util.List;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Templates {
    List<Template> all();

    Template newTemplate(String name, String tableName, String username);

    Template template(int idTemplate);

    //TODO: обновление шаблона
    //TODO: удаление шаблона
}
