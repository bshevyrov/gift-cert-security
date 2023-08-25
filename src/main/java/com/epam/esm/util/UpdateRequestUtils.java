package com.epam.esm.util;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Util class helps transfer not empty date to corresponding class.
 */
public final class UpdateRequestUtils {
    private UpdateRequestUtils() {
    }

    /**
     * Checks fields  for null or zero if field {@link Number}
     *
     * @param source inspected object.
     * @return String array with fields name.
     */
    private static String[] getNullOrEmptyPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> {
                            Object propertyValue = wrappedSource.getPropertyValue(propertyName);
                            return ObjectUtils.isEmpty(propertyValue)
                                    || objectIsNumberAndZero(propertyValue);
                        }
                )
                .toArray(String[]::new);
    }

    /**
     * Method validate if object is{@link Number} and equals zero;
     *
     * @param propertyValue inspected field.
     * @return boolean.
     */
    private static boolean objectIsNumberAndZero(Object propertyValue) {
        return ObjectUtils.isNotEmpty(propertyValue) && ClassUtils.isAssignable(Objects.requireNonNull(propertyValue).getClass(), Number.class)
                && ((Number) Objects.requireNonNull(propertyValue)).doubleValue() == 0;
    }

    /**
     * Copies not null fields from source object to target.
     *
     * @param src    object from which fields copy.
     * @param target object to which fields copy.
     */
    public static void copyNotNullOrEmptyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullOrEmptyPropertyNames(src));
    }
}
