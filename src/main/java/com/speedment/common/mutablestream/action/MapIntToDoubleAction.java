package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapIntToDoubleActionImpl;
import java.util.function.IntToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapIntToDoubleAction extends Action<Integer, IntStream, Double, DoubleStream> {
    
    IntToDoubleFunction getMapper();
    
    static MapIntToDoubleAction create(HasNext<Integer, IntStream> previous, IntToDoubleFunction mapper) {
        return new MapIntToDoubleActionImpl(previous, mapper);
    }
}