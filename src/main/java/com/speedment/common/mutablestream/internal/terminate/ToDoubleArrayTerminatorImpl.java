package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ToDoubleArrayTerminator;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ToDoubleArrayTerminatorImpl
extends AbstractTerminator<Double, DoubleStream, double[]> 
implements ToDoubleArrayTerminator {
    
    public ToDoubleArrayTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public double[] execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.toArray();
        }
    }
}