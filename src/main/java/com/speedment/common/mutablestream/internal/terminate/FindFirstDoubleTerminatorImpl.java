package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.FindFirstDoubleTerminator;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FindFirstDoubleTerminatorImpl
extends AbstractTerminator<Double, DoubleStream, OptionalDouble> 
implements FindFirstDoubleTerminator {

    public FindFirstDoubleTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public OptionalDouble execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.findFirst();
        }
    }
}