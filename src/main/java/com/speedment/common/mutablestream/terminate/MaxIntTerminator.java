package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.MaxIntTerminatorImpl;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MaxIntTerminator extends Terminator<Integer, IntStream, OptionalInt> {
    
    static MaxIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new MaxIntTerminatorImpl(previous, parallel);
    }
}