package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.NoneMatchTerminatorImpl;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface NoneMatchTerminator<T> extends Terminator<T, Stream<T>, Boolean> {
    
    Predicate<T> getPredicate();
    
    static <T> NoneMatchTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel, Predicate<T> predicate) {
        return new NoneMatchTerminatorImpl<>(previous, parallel, predicate);
    }
}