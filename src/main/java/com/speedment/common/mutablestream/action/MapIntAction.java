package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapIntActionImpl;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @param <R>  the outgoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapIntAction<R> extends Action<Integer, IntStream, R, Stream<R>> {
    
    IntFunction<R> getMapper();
    
    static <R> MapIntAction<R> create(HasNext<Integer, IntStream> previous, IntFunction<R> mapper) {
        return new MapIntActionImpl<>(previous, mapper);
    }
}