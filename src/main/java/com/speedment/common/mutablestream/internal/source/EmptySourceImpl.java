package com.speedment.common.mutablestream.internal.source;

import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.source.EmptySource;
import com.speedment.common.mutablestream.HasNext;
import java.util.OptionalInt;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * @param <TS> the type of the stream itself
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class EmptySourceImpl<T, TS extends BaseStream<T, TS>> implements EmptySource<T, TS> {
    
    private final static OptionalInt ZERO = OptionalInt.of(0);

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<T, TS, Q, QS> next) {
        @SuppressWarnings("unchecked") // An empty stream is an empty stream.
        final HasNext<Q, QS> casted = (HasNext<Q, QS>) this;
        return casted;
    }

    @Override
    public OptionalInt count() {
        return ZERO;
    }

    @Override
    public TS build() {
        return (TS) Stream.empty();
    }
}