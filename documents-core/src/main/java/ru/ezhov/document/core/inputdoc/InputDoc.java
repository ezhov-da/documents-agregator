package ru.ezhov.document.core.inputdoc;

import java.util.Iterator;

/**
 * Чтение входных данных
 *
 * @param <T> - возвращаемый тип строки
 */
public interface InputDoc<T> {
    Iterator<T> rows();
}
