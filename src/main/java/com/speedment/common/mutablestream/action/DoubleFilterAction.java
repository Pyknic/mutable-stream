package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.DoubleFilterActionImpl;
import java.util.function.DoublePredicate;
import java.util.stream.DoubleStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface DoubleFilterAction extends Action<Double, DoubleStream, Double, DoubleStream> {
    
    DoublePredicate getPredicate();
    
    static DoubleFilterAction create(HasNext<Double, DoubleStream> previous, DoublePredicate filter) {
        return new DoubleFilterActionImpl(previous, filter);
    }
}