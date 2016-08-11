package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.AllMatchLongTerminatorImpl;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface AllMatchLongTerminator extends Terminator<Long, LongStream, Boolean> {
    
    LongPredicate getPredicate();
    
    static <T> AllMatchLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel, LongPredicate predicate) {
        return new AllMatchLongTerminatorImpl(previous, parallel, predicate);
    }
}