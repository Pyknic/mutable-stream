package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.NoneMatchLongTerminatorImpl;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface NoneMatchLongTerminator extends Terminator<Long, LongStream, Boolean> {
    
    LongPredicate getPredicate();
    
    static <T> NoneMatchLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel, LongPredicate predicate) {
        return new NoneMatchLongTerminatorImpl(previous, parallel, predicate);
    }
}