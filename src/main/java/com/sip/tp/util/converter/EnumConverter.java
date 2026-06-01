package com.sip.tp.util.converter;

import jakarta.persistence.AttributeConverter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic converter for sealed type hierarchies that expose a {@code code()} method.
 *
 * <p>It builds a registry from each permitted subtype to avoid writing repetitive switch-based converters.</p>
 */
public abstract class EnumConverter<T> implements AttributeConverter<T, String> {

    private final Map<String, T> byCode;
    private final Method codeMethod;

    protected EnumConverter(Class<T> baseType) {
        this.codeMethod = resolveCodeMethod(baseType);
        this.byCode = buildRegistry(baseType, codeMethod);
    }

    private static <T> Map<String, T> buildRegistry(Class<T> baseType, Method codeMethod) {
        Class<?>[] permitted = baseType.getPermittedSubclasses();
        if (permitted == null || permitted.length == 0) {
            throw new IllegalStateException("Type must be sealed with permitted subtypes: " + baseType.getName());
        }

        Map<String, T> result = new HashMap<>();
        for (Class<?> subtype : permitted) {
            T instance = instantiateSubtype(baseType, subtype);
            String code = invokeCode(codeMethod, instance);
            T previous = result.put(code, instance);
            if (previous != null) {
                throw new IllegalStateException("Duplicate code '" + code + "' in " + baseType.getName());
            }
        }

        return Collections.unmodifiableMap(result);
    }

    private static Method resolveCodeMethod(Class<?> baseType) {
        try {
            return baseType.getMethod("code");
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Type must declare code(): " + baseType.getName(), e);
        }
    }

    private static String invokeCode(Method codeMethod, Object instance) {
        try {
            return (String) codeMethod.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot invoke code() for type: " + instance.getClass().getName(), e);
        }
    }

    private static <T> T instantiateSubtype(Class<T> baseType, Class<?> subtype) {
        try {
            Constructor<?> constructor = subtype.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();
            return baseType.cast(instance);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Subtype must have a no-arg constructor: " + subtype.getName(), e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot instantiate subtype: " + subtype.getName(), e);
        }
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (attribute == null) {
            return null;
        }

        try {
            return (String) codeMethod.invoke(attribute);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot read code() from type: " + attribute.getClass().getName(), e);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        T mapped = byCode.get(dbData);
        if (mapped == null) {
            throw new IllegalArgumentException("Unknown value for " + codeMethod.getDeclaringClass().getSimpleName() + ": " + dbData);
        }
        return mapped;
    }
}

