package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.AnyMatchDoubleTerminatorImpl;
import java.util.function.DoublePredicate;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface AnyMatchDoubleTerminator extends Terminator<Double, DoubleStream, Boolean> {
    
    DoublePredicate getPredicate();
    
    static <T> AnyMatchDoubleTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel, DoublePredicate predicate) {
        return new AnyMatchDoubleTerminatorImpl(previous, parallel, predicate);
    }
}