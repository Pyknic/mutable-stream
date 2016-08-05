package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.CollectTerminator;
import static java.util.Objects.requireNonNull;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the terminated stream type
 * @param <A>  the intermediary collector type
 * @param <R>  the resulting type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class CollectTerminatorImpl<T, A, R> 
extends AbstractTerminator<T, Stream<T>, R>
implements CollectTerminator<T, A, R> {
    
    private final Collector<T, A, R> collector;

    public CollectTerminatorImpl(HasNext<T, Stream<T>> previous, Collector<T, A, R> collector) {
        super(previous);
        this.collector = requireNonNull(collector);
    }
    
    @Override
    public Collector<T, A, R> getCollector() {
        return collector;
    }

    @Override
    public R execute() {
        try (final Stream<T> stream = previous().build()) {
            return stream.collect(collector);
        }
    }
}