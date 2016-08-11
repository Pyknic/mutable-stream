package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.DoubleIteratorTerminatorImpl;
import java.util.PrimitiveIterator;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface DoubleIteratorTerminator extends Terminator<Double, DoubleStream, PrimitiveIterator.OfDouble> {
    
    static DoubleIteratorTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel) {
        return new DoubleIteratorTerminatorImpl(previous, parallel);
    }
}