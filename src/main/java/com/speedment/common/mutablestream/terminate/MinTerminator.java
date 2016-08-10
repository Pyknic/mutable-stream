package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.MinTerminatorImpl;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MinTerminator<T> extends Terminator<T, Stream<T>, Optional<T>> {
    
    Comparator<T> getComparator();
    
    static <T> MinTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel, Comparator<T> comparator) {
        return new MinTerminatorImpl<>(previous, parallel, comparator);
    }
}