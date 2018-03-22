package ru.ezhov.document.core.table;

import ru.ezhov.document.core.util.text.Text;

public interface TableText {
    Text text();
    
    Text insert(); //хотя скорее вынести в другой класс и назвать класс как InsertTableText
}
