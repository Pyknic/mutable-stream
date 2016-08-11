package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.DoubleIteratorTerminator;
import java.util.PrimitiveIterator;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class DoubleIteratorTerminatorImpl
extends AbstractTerminator<Double, DoubleStream, PrimitiveIterator.OfDouble> 
implements DoubleIteratorTerminator {

    public DoubleIteratorTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public PrimitiveIterator.OfDouble execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.iterator();
        }
    }
}