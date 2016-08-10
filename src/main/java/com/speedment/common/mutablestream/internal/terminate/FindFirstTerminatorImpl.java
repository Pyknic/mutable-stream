package com.speedment.common.mutablestream.internal.terminate;

import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.FindFirstTerminator;
import java.util.Optional;

/**
 *
 * @param <T>  the terminated stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FindFirstTerminatorImpl<T> 
extends AbstractTerminator<T, Stream<T>, Optional<T>> 
implements FindFirstTerminator<T> {

    public FindFirstTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public Optional<T> execute() {
        try (final Stream<T> stream = previous().build(isParallel())) {
            return stream.findFirst();
        }
    }
}