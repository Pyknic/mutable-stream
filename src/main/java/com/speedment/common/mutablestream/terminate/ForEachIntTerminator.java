package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ForEachIntTerminatorImpl;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ForEachIntTerminator extends Terminator<Integer, IntStream, Void> {

    IntConsumer getConsumer();
    
    static ForEachIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel, IntConsumer consumer) {
        return new ForEachIntTerminatorImpl(previous, parallel, consumer);
    }
}