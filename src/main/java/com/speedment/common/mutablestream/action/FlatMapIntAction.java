package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.FlatMapIntActionImpl;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FlatMapIntAction extends Action<Integer, IntStream, Integer, IntStream> {

    IntFunction<IntStream> getMapper();
    
    static FlatMapIntAction create(HasNext<Integer, IntStream> previous, IntFunction<IntStream> mapper) {
        return new FlatMapIntActionImpl(previous, mapper);
    }
}