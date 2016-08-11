package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapToDoubleActionImpl;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapToDoubleAction<T> extends Action<T, Stream<T>, Double, DoubleStream> {
    
    ToDoubleFunction<T> getMapper();
    
    static <T> MapToDoubleAction<T> create(HasNext<T, Stream<T>> previous, ToDoubleFunction<T> mapper) {
        return new MapToDoubleActionImpl<>(previous, mapper);
    }
}