package com.epam.esm.util;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public final class UpdateRequestUtils {
    private UpdateRequestUtils() {
    }

    private static String[] getNullOrEmptyPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        String[] rsl =
                Stream.of(wrappedSource.getPropertyDescriptors())
                        .map(FeatureDescriptor::getName)
                        .filter(propertyName -> ObjectUtils.isEmpty(wrappedSource.getPropertyValue(propertyName))
                                || (wrappedSource.getPropertyValue(propertyName) != null && ClassUtils.isAssignable(wrappedSource.getPropertyValue(propertyName).getClass(), Number.class)
                                && ((Number) wrappedSource.getPropertyValue(propertyName)).doubleValue() == 0)
                        )
                        .toArray(String[]::new);
        return rsl;
    }
    public static void copyNotNullOrEmptyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullOrEmptyPropertyNames(src));
    }
}
