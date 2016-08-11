package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapIntToLongActionImpl;
import java.util.function.IntToLongFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapIntToLongAction extends Action<Integer, IntStream, Long, LongStream> {
    
    IntToLongFunction getMapper();
    
    static MapIntToLongAction create(HasNext<Integer, IntStream> previous, IntToLongFunction mapper) {
        return new MapIntToLongActionImpl(previous, mapper);
    }
}