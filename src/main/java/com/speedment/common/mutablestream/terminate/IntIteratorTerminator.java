package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.IntIteratorTerminatorImpl;
import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface IntIteratorTerminator extends Terminator<Integer, IntStream, PrimitiveIterator.OfInt> {
    
    static IntIteratorTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new IntIteratorTerminatorImpl(previous, parallel);
    }
}