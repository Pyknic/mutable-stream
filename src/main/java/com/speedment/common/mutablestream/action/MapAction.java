package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.internal.action.MapActionImpl;
import java.util.function.Function;
import com.speedment.common.mutablestream.HasNext;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing type
 * @param <R>  the outgoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapAction<T, R> extends Action<T, Stream<T>, R, Stream<R>> {
    
    Function<T, R> getMapper();
    
    static <T, R> MapAction<T, R> create(HasNext<T, Stream<T>> previous, Function<T, R> mapper) {
        return new MapActionImpl<>(previous, mapper);
    }
}