package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.NoneMatchLongTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class NoneMatchLongTerminatorImpl
extends AbstractTerminator<Long, LongStream, Boolean> 
implements NoneMatchLongTerminator {

    private final LongPredicate predicate;

    public NoneMatchLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel, LongPredicate predicate) {
        super(previous, parallel);
        this.predicate = requireNonNull(predicate);
    }

    @Override
    public LongPredicate getPredicate() {
        return predicate;
    }
    
    @Override
    public Boolean execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.noneMatch(predicate);
        }
    }
}