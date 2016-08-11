package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ForEachLongTerminatorImpl;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ForEachLongTerminator extends Terminator<Long, LongStream, Void> {

    LongConsumer getConsumer();
    
    static ForEachLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel, LongConsumer consumer) {
        return new ForEachLongTerminatorImpl(previous, parallel, consumer);
    }
}