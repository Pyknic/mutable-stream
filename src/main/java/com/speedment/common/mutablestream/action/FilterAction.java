package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.internal.action.FilterActionImpl;
import java.util.function.Predicate;
import com.speedment.common.mutablestream.HasNext;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the filtered type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FilterAction<T> extends Action<T, Stream<T>, T, Stream<T>> {
    
    Predicate<T> getPredicate();
    
    static <T> FilterAction<T> create(HasNext<T, Stream<T>> previous, Predicate<T> filter) {
        return new FilterActionImpl<>(previous, filter);
    }
    
}