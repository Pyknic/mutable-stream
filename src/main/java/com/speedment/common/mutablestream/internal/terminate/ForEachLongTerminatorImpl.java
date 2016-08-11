package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ForEachLongTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ForEachLongTerminatorImpl
extends AbstractTerminator<Long, LongStream, Void> 
implements ForEachLongTerminator {

    private final LongConsumer consumer;
    
    public ForEachLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel, LongConsumer consumer) {
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
            stream.forEach(consumer);
        }
        
        return null;
    }
}