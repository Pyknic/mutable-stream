package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.internal.terminate.ForEachOrderedTerminatorImpl;
import java.util.function.Consumer;
import com.speedment.common.mutablestream.HasNext;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the terminated stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface ForEachOrderedTerminator<T> extends Terminator<T, Stream<T>, Void> {

    Consumer<T> getConsumer();
    
    static <T> ForEachOrderedTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel, Consumer<T> consumer) {
        return new ForEachOrderedTerminatorImpl<>(previous, parallel, consumer);
    }
}