package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.AllMatchTerminator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class AllMatchTerminatorImpl<T> 
extends AbstractTerminator<T, Stream<T>, Boolean> 
implements AllMatchTerminator<T> {

    private final Predicate<T> predicate;

    public AllMatchTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel, Predicate<T> predicate) {
        super(previous, parallel);
        this.predicate = requireNonNull(predicate);
    }

    @Override
    public Predicate<T> getPredicate() {
        return predicate;
    }
    
    @Override
    public Boolean execute() {
        try (final Stream<T> stream = buildPrevious()) {
            return stream.allMatch(predicate);
        }
    }
}