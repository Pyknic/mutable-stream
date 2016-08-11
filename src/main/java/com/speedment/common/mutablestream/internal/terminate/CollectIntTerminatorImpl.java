package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.CollectIntTerminator;
import static java.util.Objects.requireNonNull;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class CollectIntTerminatorImpl<R> 
extends AbstractTerminator<Integer, IntStream, R> 
implements CollectIntTerminator<R>{

    private final Supplier<R> supplier;
    private final ObjIntConsumer<R> accumulator;
    private final BiConsumer<R, R> combiner;

    public CollectIntTerminatorImpl(
            HasNext<Integer, IntStream> previous, 
            boolean parallel, 
            Supplier<R> supplier, 
            ObjIntConsumer<R> accumulator, 
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
    public ObjIntConsumer<R> getAccumulator() {
        return accumulator;
    }

    @Override
    public BiConsumer<R, R> getCombiner() {
        return combiner;
    }
    
    @Override
    public R execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.collect(supplier, accumulator, combiner);
        }
    }
}