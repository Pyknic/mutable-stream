package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ForEachIntTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ForEachIntTerminatorImpl
extends AbstractTerminator<Integer, IntStream, Void> 
implements ForEachIntTerminator {

    private final IntConsumer consumer;
    
    public ForEachIntTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel, IntConsumer consumer) {
        super(previous, parallel);
        this.consumer = requireNonNull(consumer);
    }

    @Override
    public IntConsumer getConsumer() {
        return consumer;
    }

    @Override
    public Void execute() {
        try (final IntStream stream = buildPrevious()) {
            stream.forEach(consumer);
        }
        
        return null;
    }
}