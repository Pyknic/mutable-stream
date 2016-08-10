package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.FlatMapIntAction;
import java.util.function.IntFunction;
import java.util.stream.BaseStream;
import java.util.stream.IntStream;
import static java.util.Objects.requireNonNull;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FlatMapIntActionImpl
extends AbstractAction<Integer, IntStream, Integer, IntStream> 
implements FlatMapIntAction {

    private final IntFunction<IntStream> mapper;
    
    public FlatMapIntActionImpl(HasNext<Integer, IntStream> previous, IntFunction<IntStream> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public IntFunction<IntStream> getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Integer, IntStream, Q, QS> next) {
        return next;
    }

    @Override
    public IntStream build(boolean parallel) {
        return previous().build(parallel).flatMap(mapper);
    }
}