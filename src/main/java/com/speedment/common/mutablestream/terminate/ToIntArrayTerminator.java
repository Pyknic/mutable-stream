package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ToIntArrayTerminatorImpl;
import java.util.stream.IntStream;

/**
 * 
 * @author Emil Forslund
 * @since   1.0.0
 */
public interface ToIntArrayTerminator extends Terminator<Integer, IntStream, int[]> {
  
    static <T, A> ToIntArrayTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new ToIntArrayTerminatorImpl(previous, parallel);
    }
    
}