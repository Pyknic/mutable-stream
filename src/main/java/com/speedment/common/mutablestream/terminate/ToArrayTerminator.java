package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.ToArrayTerminatorImpl;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the terminated stream type
 * @param <A>  the resulting array type
 * 
 * @author Emil Forslund
 * @since   1.0.0
 */
public interface ToArrayTerminator<T, A> extends Terminator<T, Stream<T>, A[]> {
    
    IntFunction<A[]> getInstantiator();
    
    static <T, A> ToArrayTerminator<T, A> create(HasNext<T, Stream<T>> previous, boolean parallel, IntFunction<A[]> instantiator) {
        return new ToArrayTerminatorImpl<>(previous, parallel, instantiator);
    }
    
}
