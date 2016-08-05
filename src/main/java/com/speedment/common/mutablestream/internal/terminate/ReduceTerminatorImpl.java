package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ReduceTerminator;
import static java.util.Objects.requireNonNull;
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
public final class ReduceTerminatorImpl<T, U> 
extends AbstractTerminator<T, Stream<T>, U> 
implements ReduceTerminator<T, U> {
    
    private final U identity;
    private final BiFunction<U, T, U> accumulator;
    private final BinaryOperator<U> combiner;

    public ReduceTerminatorImpl(
            HasNext<T, Stream<T>> previous, 
            U identity, 
            BiFunction<U, T, U> accumulator, 
            BinaryOperator<U> combiner) {
        
        super(previous);
        this.identity    = identity;     // Can be null.
        this.accumulator = accumulator;  // Can be null.
        this.combiner    = requireNonNull(combiner); 
    }
    
    @Override
    public Optional<U> getIdentity() {
        return Optional.ofNullable(identity);
    }
    
    @Override
    public Optional<BiFunction<U, T, U>> getAccumulator() {
        return Optional.ofNullable(accumulator);
    }
    
    @Override
    public BinaryOperator<U> getCombiner() {
        return combiner;
    }

    @Override
    @SuppressWarnings("unchecked")
    public U execute() {
        try (final Stream<T> stream = previous().build()) {
            if (accumulator == null) { // T and U are the same.
                final BinaryOperator<T> tCombiner = (BinaryOperator<T>) combiner;
                
                if (identity == null) {
                    return (U) stream.reduce(tCombiner);
                } else {
                    return (U) stream.reduce((T) identity, tCombiner);
                }
            } else {
                return stream.reduce(identity, accumulator, combiner);
            }
        }
    }
}