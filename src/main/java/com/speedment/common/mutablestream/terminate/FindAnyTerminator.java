package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.FindAnyTerminatorImpl;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FindAnyTerminator<T> extends Terminator<T, Stream<T>, Optional<T>> {
    
    static <T> FindAnyTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel) {
        return new FindAnyTerminatorImpl<>(previous, parallel);
    }
    
}