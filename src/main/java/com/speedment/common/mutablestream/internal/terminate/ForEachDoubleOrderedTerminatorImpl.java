package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ForEachDoubleOrderedTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ForEachDoubleOrderedTerminatorImpl
extends AbstractTerminator<Double, DoubleStream, Void> 
implements ForEachDoubleOrderedTerminator {

    private final DoubleConsumer consumer;
    
    public ForEachDoubleOrderedTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel, DoubleConsumer consumer) {
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
            stream.forEachOrdered(consumer);
        }
        
        return null;
    }
}