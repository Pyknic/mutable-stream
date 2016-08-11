package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapDoubleToDoubleActionImpl;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapDoubleToDoubleAction extends Action<Double, DoubleStream, Double, DoubleStream> {
    
    DoubleUnaryOperator getMapper();
    
    static MapDoubleToDoubleAction create(HasNext<Double, DoubleStream> previous, DoubleUnaryOperator mapper) {
        return new MapDoubleToDoubleActionImpl(previous, mapper);
    }
}