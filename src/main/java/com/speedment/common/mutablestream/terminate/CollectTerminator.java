package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.CollectTerminatorImpl;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
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
public interface CollectTerminator<T, A, R> extends Terminator<T, Stream<T>, R> {
    
    Collector<T, A, R> getCollector();
    
    static <T, R> CollectTerminator<T, R, R> create(
            HasNext<T, Stream<T>> previous, 
            Supplier<R> supplier, 
            BiConsumer<R, ? super T> accumulator, 
            BiConsumer<R, R> combiner) {
        
        @SuppressWarnings("unchecked")
        final Collector<T, R, R> collector = (Collector<T, R, R>) 
            Collector.of(supplier, accumulator, (a, b) -> {
                combiner.accept(a, b);
                return a;
            });
        
        return create(previous, collector);
    }
    
    static <T, A, R> CollectTerminator<T, A, R> create(
            HasNext<T, Stream<T>> previous, 
            Collector<T, A, R> collector) {
        
        return new CollectTerminatorImpl<>(previous, collector);
    }
}