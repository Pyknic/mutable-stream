package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.SumIntTerminatorImpl;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface SumIntTerminator extends Terminator<Integer, IntStream, Integer> {
    
    static SumIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new SumIntTerminatorImpl(previous, parallel);
    }
}