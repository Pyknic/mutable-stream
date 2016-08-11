package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapLongToDoubleActionImpl;
import java.util.function.LongToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapLongToDoubleAction extends Action<Long, LongStream, Double, DoubleStream> {
    
    LongToDoubleFunction getMapper();
    
    static MapLongToDoubleAction create(HasNext<Long, LongStream> previous, LongToDoubleFunction mapper) {
        return new MapLongToDoubleActionImpl(previous, mapper);
    }
}