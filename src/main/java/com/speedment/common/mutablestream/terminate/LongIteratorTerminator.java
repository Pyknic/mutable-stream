package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.LongIteratorTerminatorImpl;
import java.util.PrimitiveIterator;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface LongIteratorTerminator extends Terminator<Long, LongStream, PrimitiveIterator.OfLong> {
    
    static LongIteratorTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new LongIteratorTerminatorImpl(previous, parallel);
    }
}