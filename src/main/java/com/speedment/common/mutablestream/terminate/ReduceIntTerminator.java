package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ReduceIntTerminatorImpl;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 *
 * @author Emil Forslund
 */
public interface ReduceIntTerminator extends Terminator<Integer, IntStream, OptionalInt> {
    
    OptionalInt getInitialValue();
    
    IntBinaryOperator getCombiner();
    
    static ReduceIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel, IntBinaryOperator combiner) {
        return new ReduceIntTerminatorImpl(previous, parallel, combiner);
    }
    
    static ReduceIntTerminator create(HasNext<Integer, IntStream> previous, boolean parallel, int initialValue, IntBinaryOperator combiner) {
        return new ReduceIntTerminatorImpl(previous, parallel, initialValue, combiner);
    }
    
}
