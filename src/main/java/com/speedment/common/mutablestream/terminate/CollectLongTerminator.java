package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.CollectLongTerminatorImpl;
import java.util.function.BiConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 *
 * @param <R>  the resulting type once the collector has been applied
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface CollectLongTerminator<R> extends Terminator<Long, LongStream, R> {

    Supplier<R> getSupplier();
    
    ObjLongConsumer<R> getAccumulator();
    
    BiConsumer<R, R> getCombiner();
    
    static <R> CollectLongTerminator<R> create(
            HasNext<Long, LongStream> previous, 
            boolean parallel, 
            Supplier<R> supplier, 
            ObjLongConsumer<R> accumulator, 
            BiConsumer<R, R> merger) {
        
        return new CollectLongTerminatorImpl<>(
            previous, parallel, supplier, accumulator, merger
        );
    }
}
