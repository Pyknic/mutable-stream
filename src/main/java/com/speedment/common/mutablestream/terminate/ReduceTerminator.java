package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ReduceTerminatorImpl;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 *
 * @param <T>   the streamed type
 * @param <U>   the resulting type after the reduction
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ReduceTerminator<T, U> extends Terminator<T, Stream<T>, U> {
    
    Optional<U> getIdentity();
    
    Optional<BiFunction<U, T, U>> getAccumulator();
    
    BinaryOperator<U> getCombiner();
    
    static <T> ReduceTerminator<T, T> create(
            HasNext<T, Stream<T>> previous, 
            BinaryOperator<T> combiner) {
        return new ReduceTerminatorImpl<>(previous, null, null, combiner);
    }
    
    static <T> ReduceTerminator<T, T> create(
            HasNext<T, Stream<T>> previous, 
            T identity, 
            BinaryOperator<T> combiner) {
        return new ReduceTerminatorImpl<>(previous, identity, null, combiner);
    }
    
    static <T, U> ReduceTerminator<T, U> create(
            HasNext<T, Stream<T>> previous, 
            U identity, 
            BiFunction<U, T, U> accumulator, 
            BinaryOperator<U> combiner) {
        return new ReduceTerminatorImpl<>(previous, identity, accumulator, combiner);
    }
    
}