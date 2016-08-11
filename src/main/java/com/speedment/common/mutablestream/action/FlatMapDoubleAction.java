package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.FlatMapDoubleActionImpl;
import java.util.function.DoubleFunction;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FlatMapDoubleAction extends Action<Double, DoubleStream, Double, DoubleStream> {

    DoubleFunction<DoubleStream> getMapper();
    
    static FlatMapDoubleAction create(HasNext<Double, DoubleStream> previous, DoubleFunction<DoubleStream> mapper) {
        return new FlatMapDoubleActionImpl(previous, mapper);
    }
}