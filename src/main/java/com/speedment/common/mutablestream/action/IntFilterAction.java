package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.IntFilterActionImpl;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface IntFilterAction extends Action<Integer, IntStream, Integer, IntStream> {
    
    IntPredicate getPredicate();
    
    static IntFilterAction create(HasNext<Integer, IntStream> previous, IntPredicate filter) {
        return new IntFilterActionImpl(previous, filter);
    }
}