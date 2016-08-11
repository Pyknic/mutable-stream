package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapLongActionImpl;
import java.util.function.LongFunction;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @param <R>  the outgoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapLongAction<R> extends Action<Long, LongStream, R, Stream<R>> {
    
    LongFunction<R> getMapper();
    
    static <R> MapLongAction<R> create(HasNext<Long, LongStream> previous, LongFunction<R> mapper) {
        return new MapLongActionImpl<>(previous, mapper);
    }
}