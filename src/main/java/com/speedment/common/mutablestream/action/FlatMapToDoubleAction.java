package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.FlatMapToDoubleActionImpl;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FlatMapToDoubleAction<T> extends Action<T, Stream<T>, Double, DoubleStream> {

    Function<T, DoubleStream> getMapper();
    
    static <T> FlatMapToDoubleAction<T> create(HasNext<T, Stream<T>> previous, Function<T, DoubleStream> mapper) {
        return new FlatMapToDoubleActionImpl<>(previous, mapper);
    }
}