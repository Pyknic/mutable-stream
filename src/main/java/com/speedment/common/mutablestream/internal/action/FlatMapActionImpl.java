package com.speedment.common.mutablestream.internal.action;

import java.util.function.Function;
import java.util.stream.Stream;
import java.util.OptionalInt;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.FlatMapAction;
import com.speedment.common.mutablestream.action.Action;
import static java.util.Objects.requireNonNull;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the ingoing type
 * @param <R>  inner type of the outgoing stream
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FlatMapActionImpl<T, R> 
extends AbstractAction<T, Stream<T>, R, Stream<R>> 
implements FlatMapAction<T, R> {

    private final Function<T, Stream<R>> mapper;
    
    public FlatMapActionImpl(HasNext<T, Stream<T>> previous, Function<T, Stream<R>> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public Function<T, Stream<R>> getMapper() {
        return mapper;
    }

    @Override
    public OptionalInt count() {
        // We don't know how many items will be left after the stream has been 
        // flattened.
        return OptionalInt.empty();
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<R, Stream<R>, Q, QS> next) {
        return next;
    }

    @Override
    public Stream<R> build() {
        return previous().build().flatMap(mapper);
    }
}