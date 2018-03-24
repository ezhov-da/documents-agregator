package ru.ezhov.document.core.inputdoc;

import java.io.Closeable;
import java.util.Iterator;

/**
 * Чтение входных данных
 *
 * @param <T> - возвращаемый тип строки
 */
public interface InputDoc<T> extends Closeable {
    Iterator<T> rows();
}
