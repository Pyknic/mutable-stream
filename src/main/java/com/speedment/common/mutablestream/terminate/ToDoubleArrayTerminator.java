package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ToDoubleArrayTerminatorImpl;
import java.util.stream.DoubleStream;

/**
 * 
 * @author Emil Forslund
 * @since   1.0.0
 */
public interface ToDoubleArrayTerminator extends Terminator<Double, DoubleStream, double[]> {
  
    static <T, A> ToDoubleArrayTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel) {
        return new ToDoubleArrayTerminatorImpl(previous, parallel);
    }
    
}