package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ForEachDoubleTerminatorImpl;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ForEachDoubleTerminator extends Terminator<Double, DoubleStream, Void> {

    DoubleConsumer getConsumer();
    
    static ForEachDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel, DoubleConsumer consumer) {
        return new ForEachDoubleTerminatorImpl(previous, parallel, consumer);
    }
}