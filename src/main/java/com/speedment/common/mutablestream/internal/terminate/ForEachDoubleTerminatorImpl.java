package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ForEachDoubleTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ForEachDoubleTerminatorImpl
extends AbstractTerminator<Double, DoubleStream, Void> 
implements ForEachDoubleTerminator {

    private final DoubleConsumer consumer;
    
    public ForEachDoubleTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel, DoubleConsumer consumer) {
        super(previous, parallel);
        this.consumer = requireNonNull(consumer);
    }

    @Override
    public DoubleConsumer getConsumer() {
        return consumer;
    }

    @Override
    public Void execute() {
        try (final DoubleStream stream = buildPrevious()) {
            stream.forEach(consumer);
        }
        
        return null;
    }
}