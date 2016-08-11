package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.FlatMapToLongActionImpl;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FlatMapToLongAction<T> extends Action<T, Stream<T>, Long, LongStream> {

    Function<T, LongStream> getMapper();
    
    static <T> FlatMapToLongAction<T> create(HasNext<T, Stream<T>> previous, Function<T, LongStream> mapper) {
        return new FlatMapToLongActionImpl<>(previous, mapper);
    }
}