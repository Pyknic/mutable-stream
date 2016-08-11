package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.FindFirstIntTerminatorImpl;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FindFirstIntTerminator extends Terminator<Integer, IntStream, OptionalInt> {
    
    static FindFirstIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new FindFirstIntTerminatorImpl(previous, parallel);
    }
    
}