package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import java.util.stream.DoubleStream;
import com.speedment.common.mutablestream.terminate.SumDoubleTerminator;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class SumDoubleTerminatorImpl
extends AbstractTerminator<Double, DoubleStream, Double> 
implements SumDoubleTerminator {
    
    public SumDoubleTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Double execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.sum();
        }
    }
}