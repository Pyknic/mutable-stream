package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.MaxLongTerminatorImpl;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MaxLongTerminator extends Terminator<Long, LongStream, OptionalLong> {
    
    static MaxLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new MaxLongTerminatorImpl(previous, parallel);
    }
}