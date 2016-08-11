package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.FindAnyIntTerminatorImpl;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FindAnyIntTerminator extends Terminator<Integer, IntStream, OptionalInt> {
    
    static FindAnyIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new FindAnyIntTerminatorImpl(previous, parallel);
    }
    
}