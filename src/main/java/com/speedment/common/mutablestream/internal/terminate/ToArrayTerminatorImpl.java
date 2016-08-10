package com.speedment.common.mutablestream.internal.terminate;

import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ToArrayTerminator;
import java.util.function.IntFunction;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the terminated stream type
 * @param <A>  the resulting array type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ToArrayTerminatorImpl<T, A> 
extends AbstractTerminator<T, Stream<T>, A[]> 
implements ToArrayTerminator<T, A> {

    private final IntFunction<A[]> instantiator;
    
    public ToArrayTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel, IntFunction<A[]> consumer) {
        super(previous, parallel);
        this.instantiator = requireNonNull(consumer);
    }

    @Override
    public IntFunction<A[]> getInstantiator() {
        return instantiator;
    }

    @Override
    public A[] execute() {
        try (final Stream<T> stream = previous().build(isParallel())) {
            return stream.toArray(instantiator);
        }
    }
}