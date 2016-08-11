package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.AnyMatchLongTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class AnyMatchLongTerminatorImpl
extends AbstractTerminator<Long, LongStream, Boolean> 
implements AnyMatchLongTerminator {

    private final LongPredicate predicate;

    public AnyMatchLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel, LongPredicate predicate) {
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
            return stream.anyMatch(predicate);
        }
    }
}