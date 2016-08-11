package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.FlatMapDoubleAction;
import java.util.function.DoubleFunction;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import static java.util.Objects.requireNonNull;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FlatMapDoubleActionImpl
extends AbstractAction<Double, DoubleStream, Double, DoubleStream> 
implements FlatMapDoubleAction {

    private final DoubleFunction<DoubleStream> mapper;
    
    public FlatMapDoubleActionImpl(HasNext<Double, DoubleStream> previous, DoubleFunction<DoubleStream> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public DoubleFunction<DoubleStream> getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Double, DoubleStream, Q, QS> next) {
        return next;
    }

    @Override
    public DoubleStream build(boolean parallel) {
        return previous().build(parallel).flatMap(mapper);
    }
}