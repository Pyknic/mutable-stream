package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.SpliteratorTerminatorImpl;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface SpliteratorTerminator<T> extends Terminator<T, Stream<T>, Spliterator<T>> {
    
    static <T> SpliteratorTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel) {
        return new SpliteratorTerminatorImpl<>(previous, parallel);
    }
}