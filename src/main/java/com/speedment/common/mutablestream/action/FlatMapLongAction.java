package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.FlatMapLongActionImpl;
import java.util.function.LongFunction;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FlatMapLongAction extends Action<Long, LongStream, Long, LongStream> {

    LongFunction<LongStream> getMapper();
    
    static FlatMapLongAction create(HasNext<Long, LongStream> previous, LongFunction<LongStream> mapper) {
        return new FlatMapLongActionImpl(previous, mapper);
    }
}