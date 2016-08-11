package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ReduceLongTerminatorImpl;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

/**
 *
 * @author Emil Forslund
 */
public interface ReduceLongTerminator extends Terminator<Long, LongStream, OptionalLong> {
    
    OptionalLong getInitialValue();
    
    LongBinaryOperator getCombiner();
    
    static ReduceLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel, LongBinaryOperator combiner) {
        return new ReduceLongTerminatorImpl(previous, parallel, combiner);
    }
    
    static ReduceLongTerminator create(HasNext<Long, LongStream> previous, boolean parallel, long initialValue, LongBinaryOperator combiner) {
        return new ReduceLongTerminatorImpl(previous, parallel, initialValue, combiner);
    }
    
}
