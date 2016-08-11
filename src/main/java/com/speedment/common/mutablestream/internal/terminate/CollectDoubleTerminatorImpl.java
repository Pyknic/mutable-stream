package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.CollectDoubleTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class CollectDoubleTerminatorImpl<R> 
extends AbstractTerminator<Double, DoubleStream, R> 
implements CollectDoubleTerminator<R>{

    private final Supplier<R> supplier;
    private final ObjDoubleConsumer<R> accumulator;
    private final BiConsumer<R, R> combiner;

    public CollectDoubleTerminatorImpl(
            HasNext<Double, DoubleStream> previous, 
            boolean parallel, 
            Supplier<R> supplier, 
            ObjDoubleConsumer<R> accumulator, 
            BiConsumer<R, R> combiner) {
        
        super(previous, parallel);
        this.supplier    = requireNonNull(supplier);
        this.accumulator = requireNonNull(accumulator);
        this.combiner    = requireNonNull(combiner);
    }
    
    @Override
    public Supplier<R> getSupplier() {
        return supplier;
    }

    @Override
    public ObjDoubleConsumer<R> getAccumulator() {
        return accumulator;
    }

    @Override
    public BiConsumer<R, R> getCombiner() {
        return combiner;
    }
    
    @Override
    public R execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.collect(supplier, accumulator, combiner);
        }
    }
}