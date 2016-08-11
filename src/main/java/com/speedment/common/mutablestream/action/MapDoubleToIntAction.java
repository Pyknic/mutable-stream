package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapDoubleToIntActionImpl;
import java.util.function.DoubleToIntFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapDoubleToIntAction extends Action<Double, DoubleStream, Integer, IntStream> {
    
    DoubleToIntFunction getMapper();
    
    static MapDoubleToIntAction create(HasNext<Double, DoubleStream> previous, DoubleToIntFunction mapper) {
        return new MapDoubleToIntActionImpl(previous, mapper);
    }
}