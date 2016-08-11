package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.MinTerminator;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MinTerminatorImpl<T> 
extends AbstractTerminator<T, Stream<T>, Optional<T>> 
implements MinTerminator<T> {

    private final Comparator<T> comparator;
    
    public MinTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel, Comparator<T> comparator) {
        super(previous, parallel);
        this.comparator = requireNonNull(comparator);
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }
    
    @Override
    public Optional<T> execute() {
        try (final Stream<T> stream = buildPrevious()) {
            return stream.min(comparator);
        }
    }
}