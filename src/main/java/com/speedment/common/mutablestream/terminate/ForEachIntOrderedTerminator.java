package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ForEachIntOrderedTerminatorImpl;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ForEachIntOrderedTerminator extends Terminator<Integer, IntStream, Void> {

    IntConsumer getConsumer();
    
    static ForEachIntOrderedTerminator create(HasNext<Integer, IntStream> previous, boolean parallel, IntConsumer consumer) {
        return new ForEachIntOrderedTerminatorImpl(previous, parallel, consumer);
    }
}