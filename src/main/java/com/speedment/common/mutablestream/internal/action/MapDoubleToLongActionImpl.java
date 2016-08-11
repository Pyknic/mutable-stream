package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapDoubleToLongAction;
import com.speedment.common.mutablestream.action.SkipAction;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import static java.util.Objects.requireNonNull;
import java.util.function.DoubleToLongFunction;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MapDoubleToLongActionImpl
extends AbstractAction<Double, DoubleStream, Long, LongStream> 
implements MapDoubleToLongAction {
    
    private final DoubleToLongFunction mapper;

    public MapDoubleToLongActionImpl(HasNext<Double, DoubleStream> previous, DoubleToLongFunction mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public DoubleToLongFunction getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Long, LongStream, Q, QS> next) {
        // If the next builder is a LimitBuilder, flip our positions so that it
        // is executed first.
        return next.ifLimit().map(limit -> {

            final LimitAction<Double, DoubleStream> newLimit = LimitAction.create(previous(), limit.getLimit());
            final HasNext<Double, DoubleStream> newPrevious = previous().append(newLimit);
            final MapDoubleToLongAction newMap = MapDoubleToLongAction.create(newPrevious, mapper);
            
            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newLimit.append(newMap);
            
            return result;
            
        // If the next builder is a SkipBuilder, flip our positions so that it
        // is executed first.
        }).orElseGet(() -> next.ifSkip().map(skip -> {

            final SkipAction<Double, DoubleStream> newSkip = SkipAction.create(previous(), skip.getSkip());
            final HasNext<Double, DoubleStream> newPrevious = previous().append(newSkip);
            final MapDoubleToLongAction newMap = MapDoubleToLongAction.create(newPrevious, mapper);

            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newSkip.append(newMap);

            return result;
            
        // The next builder is not either skip or limit. Return it so that the
        // order is preserved.
        }).orElse(next));
    }

    @Override
    public LongStream build(boolean parallel) {
        return previous().build(parallel).mapToLong(mapper);
    }
}