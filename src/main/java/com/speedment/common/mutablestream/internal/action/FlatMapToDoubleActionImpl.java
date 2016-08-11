package com.speedment.common.mutablestream.internal.action;

import java.util.function.Function;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.FlatMapToDoubleAction;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the ingoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FlatMapToDoubleActionImpl<T> 
extends AbstractAction<T, Stream<T>, Double, DoubleStream> 
implements FlatMapToDoubleAction<T> {

    private final Function<T, DoubleStream> mapper;
    
    public FlatMapToDoubleActionImpl(HasNext<T, Stream<T>> previous, Function<T, DoubleStream> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public Function<T, DoubleStream> getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Double, DoubleStream, Q, QS> next) {
        return next;
    }

    @Override
    public DoubleStream build(boolean parallel) {
        return previous().build(parallel).flatMapToDouble(mapper);
    }
}