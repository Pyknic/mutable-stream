package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.SpliteratorTerminator;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class SpliteratorTerminatorImpl<T> 
extends AbstractTerminator<T, Stream<T>, Spliterator<T>> 
implements SpliteratorTerminator<T> {

    public SpliteratorTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public Spliterator<T> execute() {
        try (final Stream<T> stream = previous().build(isParallel())) {
            return stream.spliterator();
        }
    }
}