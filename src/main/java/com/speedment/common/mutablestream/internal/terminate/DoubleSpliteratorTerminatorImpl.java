package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.DoubleSpliteratorTerminator;
import java.util.Spliterator;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class DoubleSpliteratorTerminatorImpl 
extends AbstractTerminator<Double, DoubleStream, Spliterator.OfDouble>
implements DoubleSpliteratorTerminator {

    public DoubleSpliteratorTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public Spliterator.OfDouble execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.spliterator();
        }
    }
}