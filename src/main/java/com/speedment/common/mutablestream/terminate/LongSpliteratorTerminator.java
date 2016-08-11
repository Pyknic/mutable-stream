package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.LongSpliteratorTerminatorImpl;
import java.util.Spliterator;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface LongSpliteratorTerminator extends Terminator<Long, LongStream, Spliterator.OfLong> {
    
    static LongSpliteratorTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new LongSpliteratorTerminatorImpl(previous, parallel);
    }
}