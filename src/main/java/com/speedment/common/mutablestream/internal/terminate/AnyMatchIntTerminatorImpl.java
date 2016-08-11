package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.AnyMatchIntTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class AnyMatchIntTerminatorImpl
extends AbstractTerminator<Integer, IntStream, Boolean> 
implements AnyMatchIntTerminator {

    private final IntPredicate predicate;

    public AnyMatchIntTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel, IntPredicate predicate) {
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
            return stream.anyMatch(predicate);
        }
    }
}