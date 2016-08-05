package com.speedment.common.mutablestream.internal.action;

import java.util.OptionalInt;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.SkipAction;
import static java.util.Objects.requireNonNull;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 *
 * @param <T>  the filtered type
 * @param <TS> the stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class SkipActionImpl<T, TS extends BaseStream<T, TS>> 
extends AbstractAction<T, TS, T, TS> 
implements SkipAction<T, TS> {

    private final long skip;
    
    public SkipActionImpl(HasNext<T, TS> previous, long skip) {
        super(previous);
        this.skip = requireNonNull(skip);
    }
    
    @Override
    public long getSkip() {
        return skip;
    }

    @Override
    public OptionalInt count() {
        // If the preceeding action had a fixed count, reduce that count with
        // the skip to determine the count after this action.
        final OptionalInt previousLength = previous().count();
        if (previousLength.isPresent()) {
            return OptionalInt.of(
                // Avoid negative numbers.
                (int) Math.max(previousLength.getAsInt() - skip, 0)
            );
            
        // Otherwise, we don't know the count.
        } else {
            return OptionalInt.empty();
        }
    }
    
    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<T, TS, Q, QS> next) {
        return next;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TS build() {
        final TS built = previous().build();
        if (built instanceof Stream<?>) {
            return (TS) ((Stream<T>) built).skip(skip);
        } else if (built instanceof IntStream) {
            return (TS) ((IntStream) built).skip(skip);
        } else if (built instanceof LongStream) {
            return (TS) ((LongStream) built).skip(skip);
        } else if (built instanceof DoubleStream) {
            return (TS) ((DoubleStream) built).skip(skip);
        } else {
            throw new UnsupportedOperationException(
                "Built stream did not match any known stream interface."
            );
        }
    }
}