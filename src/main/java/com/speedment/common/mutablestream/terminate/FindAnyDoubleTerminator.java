package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.FindAnyDoubleTerminatorImpl;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FindAnyDoubleTerminator extends Terminator<Double, DoubleStream, OptionalDouble> {
    
    static FindAnyDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel) {
        return new FindAnyDoubleTerminatorImpl(previous, parallel);
    }
    
}