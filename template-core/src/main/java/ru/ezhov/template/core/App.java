package ru.ezhov.template.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ru.ezhov.template.core.template.DbTemplates;

import java.util.logging.Logger;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public class App {
    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {
            new DbTemplates(new H2Source())
                    .all()
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        App app = new App();
//        String jsonRaw = testJSon();
//
//        JsonParser parser = new JsonParser();
//
//        JsonElement jsonElement = parser.parse(jsonRaw);
//        processArray(jsonElement);
//
//        System.out.println(template.rowCount());
//
//        Workbook workbook = new Workbook(new Sheet());
    }

    public void processArray(JsonElement jsonElement) {
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JsonElement element = jsonArray.get(i);
            if (element.isJsonArray()) {
                processArray(element);
            } else {
                System.out.println(element.getAsString());
            }
        }
    }

}
