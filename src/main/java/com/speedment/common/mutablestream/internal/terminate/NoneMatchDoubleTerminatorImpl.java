package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.NoneMatchDoubleTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.DoublePredicate;
import java.util.stream.DoubleStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class NoneMatchDoubleTerminatorImpl
extends AbstractTerminator<Double, DoubleStream, Boolean> 
implements NoneMatchDoubleTerminator {

    private final DoublePredicate predicate;

    public NoneMatchDoubleTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel, DoublePredicate predicate) {
        super(previous, parallel);
        this.predicate = requireNonNull(predicate);
    }

    @Override
    public DoublePredicate getPredicate() {
        return predicate;
    }
    
    @Override
    public Boolean execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.noneMatch(predicate);
        }
    }
}