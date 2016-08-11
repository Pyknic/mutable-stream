package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.MinDoubleTerminatorImpl;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MinDoubleTerminator extends Terminator<Double, DoubleStream, OptionalDouble> {
    
    static MinDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel) {
        return new MinDoubleTerminatorImpl(previous, parallel);
    }
}