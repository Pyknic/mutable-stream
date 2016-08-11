package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.AllMatchIntTerminatorImpl;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface AllMatchIntTerminator extends Terminator<Integer, IntStream, Boolean> {
    
    IntPredicate getPredicate();
    
    static <T> AllMatchIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel, IntPredicate predicate) {
        return new AllMatchIntTerminatorImpl(previous, parallel, predicate);
    }
}