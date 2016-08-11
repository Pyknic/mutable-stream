package com.speedment.common.mutablestream.internal.action;

import java.util.function.Function;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.FlatMapToLongAction;
import java.util.stream.BaseStream;
import java.util.stream.LongStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the ingoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FlatMapToLongActionImpl<T> 
extends AbstractAction<T, Stream<T>, Long, LongStream> 
implements FlatMapToLongAction<T> {

    private final Function<T, LongStream> mapper;
    
    public FlatMapToLongActionImpl(HasNext<T, Stream<T>> previous, Function<T, LongStream> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public Function<T, LongStream> getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Long, LongStream, Q, QS> next) {
        return next;
    }

    @Override
    public LongStream build(boolean parallel) {
        return previous().build(parallel).flatMapToLong(mapper);
    }
}