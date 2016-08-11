package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.AllMatchIntTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class AllMatchIntTerminatorImpl
extends AbstractTerminator<Integer, IntStream, Boolean> 
implements AllMatchIntTerminator {

    private final IntPredicate predicate;

    public AllMatchIntTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel, IntPredicate predicate) {
        super(previous, parallel);
        this.predicate = requireNonNull(predicate);
    }

    @Override
    public IntPredicate getPredicate() {
        return predicate;
    }
    
    @Override
    public Boolean execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.allMatch(predicate);
        }
    }
}