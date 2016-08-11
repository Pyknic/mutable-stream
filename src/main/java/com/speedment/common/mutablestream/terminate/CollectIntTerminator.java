package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.CollectIntTerminatorImpl;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 *
 * @param <R>  the resulting type once the collector has been applied
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface CollectIntTerminator<R> extends Terminator<Integer, IntStream, R> {

    Supplier<R> getSupplier();
    
    ObjIntConsumer<R> getAccumulator();
    
    BiConsumer<R, R> getCombiner();
    
    static <R> CollectIntTerminator<R> create(
            HasNext<Integer, IntStream> previous, 
            boolean parallel, 
            Supplier<R> supplier, 
            ObjIntConsumer<R> accumulator, 
            BiConsumer<R, R> merger) {
        
        return new CollectIntTerminatorImpl<>(
            previous, parallel, supplier, accumulator, merger
        );
    }
}
