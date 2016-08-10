package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.internal.terminate.ForEachTerminatorImpl;
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
public interface ForEachTerminator<T> extends Terminator<T, Stream<T>, Void> {

    Consumer<T> getConsumer();
    
    static <T> ForEachTerminator<T> create(HasNext<T, Stream<T>> previous, boolean parallel, Consumer<T> consumer) {
        return new ForEachTerminatorImpl<>(previous, parallel, consumer);
    }
}