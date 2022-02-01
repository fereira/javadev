package edu.cornell.library.misc.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional; 

public interface ResponseParser {
    <T> List<T> parseToList(Type type, Response response);
    <T> Optional<T> parseToObject(Class<T> clazz, Response response);
    <T> Map<String, T> parseToMap(Class<T> clazz, Response response);
}
