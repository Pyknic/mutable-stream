package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.IntSpliteratorTerminatorImpl;
import java.util.Spliterator;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface IntSpliteratorTerminator extends Terminator<Integer, IntStream, Spliterator.OfInt> {
    
    static IntSpliteratorTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new IntSpliteratorTerminatorImpl(previous, parallel);
    }
}