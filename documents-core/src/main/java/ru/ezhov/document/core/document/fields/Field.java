package ru.ezhov.document.core.document.fields;

public interface Field {

    int id();

    int idDocument();

    String name();
        
        String description();
        
        boolean active();
        
        boolean key();
        
        String columnName();
        
        FieldType type();
        
        int length();
        
        boolean empty();
        
        Order order();
        
        String username();
        
        Date addDt();
}
