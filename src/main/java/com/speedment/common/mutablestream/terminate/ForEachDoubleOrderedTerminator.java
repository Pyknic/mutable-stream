package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ForEachDoubleOrderedTerminatorImpl;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ForEachDoubleOrderedTerminator extends Terminator<Double, DoubleStream, Void> {

    DoubleConsumer getConsumer();
    
    static ForEachDoubleOrderedTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel, DoubleConsumer consumer) {
        return new ForEachDoubleOrderedTerminatorImpl(previous, parallel, consumer);
    }
}