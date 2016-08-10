package com.speedment.common.mutablestream.internal.action;

import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.SkipAction;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import static java.util.Objects.requireNonNull;

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
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<T, TS, Q, QS> next) {
        return next;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TS build(boolean parallel) {
        final TS built = previous().build(parallel);
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