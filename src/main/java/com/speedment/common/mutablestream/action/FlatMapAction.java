package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.internal.action.FlatMapActionImpl;
import java.util.function.Function;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;

/**
 *
 * @param <T>  the ingoing stream type
 * @param <R>  the outgoing stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FlatMapAction<T, R> extends Action<T, Stream<T>, R, Stream<R>> {

    Function<T, Stream<R>> getMapper();
    
    static <T, R> FlatMapAction<T, R> create(HasNext<T, Stream<T>> previous, Function<T, Stream<R>> mapper) {
        return new FlatMapActionImpl<>(previous, mapper);
    }
}