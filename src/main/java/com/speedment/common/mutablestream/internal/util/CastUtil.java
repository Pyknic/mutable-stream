package com.speedment.common.mutablestream.internal.util;

import java.util.Optional;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class CastUtil {
    
    public static <T> Optional<T> castIf(Object obj, Class<T> type) {
        if (type.isInstance(obj)) {
            @SuppressWarnings("unchecked")
            final T casted = (T) obj;
            return Optional.of(casted);
        } else {
            return Optional.empty();
        }
    }
    
    private CastUtil() {}
}
