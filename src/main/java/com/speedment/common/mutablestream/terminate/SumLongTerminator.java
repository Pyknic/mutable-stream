package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.SumLongTerminatorImpl;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface SumLongTerminator extends Terminator<Long, LongStream, Long> {
    
    static SumLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new SumLongTerminatorImpl(previous, parallel);
    }
}