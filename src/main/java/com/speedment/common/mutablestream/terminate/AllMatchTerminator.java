package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.AllMatchTerminatorImpl;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface AllMatchTerminator<T> extends Terminator<T, Stream<T>, Boolean> {
    
    Predicate<T> getPredicate();
    
    static <T> AllMatchTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel, Predicate<T> predicate) {
        return new AllMatchTerminatorImpl<>(previous, parallel, predicate);
    }
}