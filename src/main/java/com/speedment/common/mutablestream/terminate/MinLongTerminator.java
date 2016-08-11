package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.MinLongTerminatorImpl;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MinLongTerminator extends Terminator<Long, LongStream, OptionalLong> {
    
    static MinLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new MinLongTerminatorImpl(previous, parallel);
    }
}