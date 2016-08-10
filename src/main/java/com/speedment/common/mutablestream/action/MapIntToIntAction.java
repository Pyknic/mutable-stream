package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapIntToIntActionImpl;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapIntToIntAction extends Action<Integer, IntStream, Integer, IntStream> {
    
    IntUnaryOperator getMapper();
    
    static MapIntToIntAction create(HasNext<Integer, IntStream> previous, IntUnaryOperator mapper) {
        return new MapIntToIntActionImpl(previous, mapper);
    }
}