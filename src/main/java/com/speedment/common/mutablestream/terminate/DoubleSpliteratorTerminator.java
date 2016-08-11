package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.DoubleSpliteratorTerminatorImpl;
import java.util.Spliterator;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface DoubleSpliteratorTerminator extends Terminator<Double, DoubleStream, Spliterator.OfDouble> {
    
    static DoubleSpliteratorTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel) {
        return new DoubleSpliteratorTerminatorImpl(previous, parallel);
    }
}