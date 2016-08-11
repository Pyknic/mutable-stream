package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ForEachLongOrderedTerminatorImpl;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ForEachLongOrderedTerminator extends Terminator<Long, LongStream, Void> {

    LongConsumer getConsumer();
    
    static ForEachLongOrderedTerminator create(HasNext<Long, LongStream> previous, boolean parallel, LongConsumer consumer) {
        return new ForEachLongOrderedTerminatorImpl(previous, parallel, consumer);
    }
}