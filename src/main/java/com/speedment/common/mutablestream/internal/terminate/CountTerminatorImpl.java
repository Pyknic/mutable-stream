package com.speedment.common.mutablestream.internal.terminate;

import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.CountTerminator;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 *
 * @param <T>  the terminated stream type
 * @param <TS> the main stream interface
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class CountTerminatorImpl<T, TS extends BaseStream<T, TS>> 
extends AbstractTerminator<T, TS, Long> 
implements CountTerminator<T, TS> {

    public CountTerminatorImpl(HasNext<T, TS> previous) {
        super(previous);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long execute() {
        try (final TS built = previous().build()) {
            if (built instanceof Stream<?>) {
                return ((Stream<T>) built).count();
            } else if (built instanceof IntStream) {
                return ((IntStream) built).count();
            } else if (built instanceof LongStream) {
                return ((LongStream) built).count();
            } else if (built instanceof DoubleStream) {
                return ((DoubleStream) built).count();
            } else {
                throw new UnsupportedOperationException(
                    "Built stream did not match any known stream interface."
                );
            }
        }
    }
}