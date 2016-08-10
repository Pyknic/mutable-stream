package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.FindFirstTerminatorImpl;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface FindFirstTerminator<T> extends Terminator<T, Stream<T>, Optional<T>> {
    
    static <T> FindFirstTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel) {
        return new FindFirstTerminatorImpl<>(previous, parallel);
    }
    
}