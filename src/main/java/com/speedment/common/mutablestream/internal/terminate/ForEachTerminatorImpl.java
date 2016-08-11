package com.speedment.common.mutablestream.internal.terminate;

import java.util.function.Consumer;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ForEachTerminator;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the terminated stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ForEachTerminatorImpl<T> 
extends AbstractTerminator<T, Stream<T>, Void> 
implements ForEachTerminator<T> {

    private final Consumer<T> consumer;
    
    public ForEachTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel, Consumer<T> consumer) {
        super(previous, parallel);
        this.consumer = requireNonNull(consumer);
    }

    @Override
    public Consumer<T> getConsumer() {
        return consumer;
    }

    @Override
    public Void execute() {
        try (final Stream<T> stream = buildPrevious()) {
            stream.forEach(consumer);
        }
        
        return null;
    }
}