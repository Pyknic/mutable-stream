package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.MinDoubleTerminator;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MinDoubleTerminatorImpl 
extends AbstractTerminator<Double, DoubleStream, OptionalDouble>
implements MinDoubleTerminator {

    public MinDoubleTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public OptionalDouble execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.min();
        }
    }
}