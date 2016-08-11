package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ForEachLongOrderedTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ForEachLongOrderedTerminatorImpl
extends AbstractTerminator<Long, LongStream, Void> 
implements ForEachLongOrderedTerminator {

    private final LongConsumer consumer;
    
    public ForEachLongOrderedTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel, LongConsumer consumer) {
        super(previous, parallel);
        this.consumer = requireNonNull(consumer);
    }

    @Override
    public LongConsumer getConsumer() {
        return consumer;
    }

    @Override
    public Void execute() {
        try (final LongStream stream = buildPrevious()) {
            stream.forEachOrdered(consumer);
        }
        
        return null;
    }
}