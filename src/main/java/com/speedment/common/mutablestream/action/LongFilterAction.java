package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.LongFilterActionImpl;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface LongFilterAction extends Action<Long, LongStream, Long, LongStream> {
    
    LongPredicate getPredicate();
    
    static LongFilterAction create(HasNext<Long, LongStream> previous, LongPredicate filter) {
        return new LongFilterActionImpl(previous, filter);
    }
}