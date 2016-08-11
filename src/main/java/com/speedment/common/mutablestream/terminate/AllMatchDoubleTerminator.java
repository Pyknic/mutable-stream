package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.AllMatchDoubleTerminatorImpl;
import java.util.function.DoublePredicate;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface AllMatchDoubleTerminator extends Terminator<Double, DoubleStream, Boolean> {
    
    DoublePredicate getPredicate();
    
    static <T> AllMatchDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel, DoublePredicate predicate) {
        return new AllMatchDoubleTerminatorImpl(previous, parallel, predicate);
    }
}