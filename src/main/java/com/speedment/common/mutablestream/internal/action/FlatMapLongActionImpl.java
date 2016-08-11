package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.FlatMapLongAction;
import java.util.function.LongFunction;
import java.util.stream.BaseStream;
import java.util.stream.LongStream;
import static java.util.Objects.requireNonNull;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FlatMapLongActionImpl
extends AbstractAction<Long, LongStream, Long, LongStream> 
implements FlatMapLongAction {

    private final LongFunction<LongStream> mapper;
    
    public FlatMapLongActionImpl(HasNext<Long, LongStream> previous, LongFunction<LongStream> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public LongFunction<LongStream> getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Long, LongStream, Q, QS> next) {
        return next;
    }

    @Override
    public LongStream build(boolean parallel) {
        return previous().build(parallel).flatMap(mapper);
    }
}