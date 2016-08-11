package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.SumDoubleTerminatorImpl;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface SumDoubleTerminator extends Terminator<Double, DoubleStream, Double> {
    
    static SumDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel) {
        return new SumDoubleTerminatorImpl(previous, parallel);
    }
}