package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.CollectDoubleTerminatorImpl;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/**
 *
 * @param <R>  the resulting type once the collector has been applied
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface CollectDoubleTerminator<R> extends Terminator<Double, DoubleStream, R> {

    Supplier<R> getSupplier();
    
    ObjDoubleConsumer<R> getAccumulator();
    
    BiConsumer<R, R> getCombiner();
    
    static <R> CollectDoubleTerminator<R> create(
            HasNext<Double, DoubleStream> previous, 
            boolean parallel, 
            Supplier<R> supplier, 
            ObjDoubleConsumer<R> accumulator, 
            BiConsumer<R, R> merger) {
        
        return new CollectDoubleTerminatorImpl<>(
            previous, parallel, supplier, accumulator, merger
        );
    }
}
