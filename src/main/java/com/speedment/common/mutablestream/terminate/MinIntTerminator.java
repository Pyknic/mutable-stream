package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.MinIntTerminatorImpl;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MinIntTerminator extends Terminator<Integer, IntStream, OptionalInt> {
    
    static MinIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new MinIntTerminatorImpl(previous, parallel);
    }
}