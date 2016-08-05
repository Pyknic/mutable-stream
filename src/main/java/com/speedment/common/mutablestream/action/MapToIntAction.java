package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapToIntActionImpl;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapToIntAction<T> extends Action<T, Stream<T>, Integer, IntStream> {
    
    ToIntFunction<T> getMapper();
    
    static <T> MapToIntAction<T> create(HasNext<T, Stream<T>> previous, ToIntFunction<T> mapper) {
        return new MapToIntActionImpl<>(previous, mapper);
    }
}