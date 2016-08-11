package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ToLongArrayTerminatorImpl;
import java.util.stream.LongStream;

/**
 * 
 * @author Emil Forslund
 * @since   1.0.0
 */
public interface ToLongArrayTerminator extends Terminator<Long, LongStream, long[]> {
  
    static <T, A> ToLongArrayTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new ToLongArrayTerminatorImpl(previous, parallel);
    }
    
}