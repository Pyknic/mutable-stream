package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapDoubleActionImpl;
import java.util.function.DoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 *
 * @param <R>  the outgoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapDoubleAction<R> extends Action<Double, DoubleStream, R, Stream<R>> {
    
    DoubleFunction<R> getMapper();
    
    static <R> MapDoubleAction<R> create(HasNext<Double, DoubleStream> previous, DoubleFunction<R> mapper) {
        return new MapDoubleActionImpl<>(previous, mapper);
    }
}