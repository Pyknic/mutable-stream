package com.speedment.common.mutablestream.internal.action;

import java.util.function.Function;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.FlatMapToIntAction;
import java.util.stream.BaseStream;
import java.util.stream.IntStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the ingoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FlatMapToIntActionImpl<T> 
extends AbstractAction<T, Stream<T>, Integer, IntStream> 
implements FlatMapToIntAction<T> {

    private final Function<T, IntStream> mapper;
    
    public FlatMapToIntActionImpl(HasNext<T, Stream<T>> previous, Function<T, IntStream> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public Function<T, IntStream> getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Integer, IntStream, Q, QS> next) {
        return next;
    }

    @Override
    public IntStream build(boolean parallel) {
        return previous().build(parallel).flatMapToInt(mapper);
    }
}