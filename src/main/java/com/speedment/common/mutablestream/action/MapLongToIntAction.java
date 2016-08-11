package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapLongToIntActionImpl;
import java.util.function.LongToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapLongToIntAction extends Action<Long, LongStream, Integer, IntStream> {
    
    LongToIntFunction getMapper();
    
    static MapLongToIntAction create(HasNext<Long, LongStream> previous, LongToIntFunction mapper) {
        return new MapLongToIntActionImpl(previous, mapper);
    }
}