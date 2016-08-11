package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.FindFirstLongTerminatorImpl;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FindFirstLongTerminator extends Terminator<Long, LongStream, OptionalLong> {
    
    static FindFirstLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new FindFirstLongTerminatorImpl(previous, parallel);
    }
    
}