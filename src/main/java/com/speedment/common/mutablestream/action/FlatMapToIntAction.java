package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.FlatMapToIntActionImpl;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FlatMapToIntAction<T> extends Action<T, Stream<T>, Integer, IntStream> {

    Function<T, IntStream> getMapper();
    
    static <T> FlatMapToIntAction<T> create(HasNext<T, Stream<T>> previous, Function<T, IntStream> mapper) {
        return new FlatMapToIntActionImpl<>(previous, mapper);
    }
}