package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapToLongActionImpl;
import java.util.function.ToLongFunction;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapToLongAction<T> extends Action<T, Stream<T>, Long, LongStream> {
    
    ToLongFunction<T> getMapper();
    
    static <T> MapToLongAction<T> create(HasNext<T, Stream<T>> previous, ToLongFunction<T> mapper) {
        return new MapToLongActionImpl<>(previous, mapper);
    }
}