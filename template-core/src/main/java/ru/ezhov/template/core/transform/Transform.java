package ru.ezhov.template.core.transform;

/**
 * Created by ezhov_da on 19.03.2018.
 */
public interface Transform<T> {
    T value() throws Exception;
}
