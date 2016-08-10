package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.IteratorTerminatorImpl;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface IteratorTerminator<T> extends Terminator<T, Stream<T>, Iterator<T>> {
    
    static <T> IteratorTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel) {
        return new IteratorTerminatorImpl<>(previous, parallel);
    }
}