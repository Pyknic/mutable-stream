package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapDoubleToLongActionImpl;
import java.util.function.DoubleToLongFunction;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapDoubleToLongAction extends Action<Double, DoubleStream, Long, LongStream> {
    
    DoubleToLongFunction getMapper();
    
    static MapDoubleToLongAction create(HasNext<Double, DoubleStream> previous, DoubleToLongFunction mapper) {
        return new MapDoubleToLongActionImpl(previous, mapper);
    }
}