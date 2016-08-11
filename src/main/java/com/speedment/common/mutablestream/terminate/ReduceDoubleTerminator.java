package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ReduceDoubleTerminatorImpl;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.DoubleStream;

/**
 *
 * @author Emil Forslund
 */
public interface ReduceDoubleTerminator extends Terminator<Double, DoubleStream, OptionalDouble> {
    
    OptionalDouble getInitialValue();
    
    DoubleBinaryOperator getCombiner();
    
    static ReduceDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel, DoubleBinaryOperator combiner) {
        return new ReduceDoubleTerminatorImpl(previous, parallel, combiner);
    }
    
    static ReduceDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel, double initialValue, DoubleBinaryOperator combiner) {
        return new ReduceDoubleTerminatorImpl(previous, parallel, initialValue, combiner);
    }
    
}
