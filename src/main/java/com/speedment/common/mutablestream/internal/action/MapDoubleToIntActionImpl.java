package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapDoubleToIntAction;
import com.speedment.common.mutablestream.action.SkipAction;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import static java.util.Objects.requireNonNull;
import java.util.function.DoubleToIntFunction;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MapDoubleToIntActionImpl
extends AbstractAction<Double, DoubleStream, Integer, IntStream> 
implements MapDoubleToIntAction {
    
    private final DoubleToIntFunction mapper;

    public MapDoubleToIntActionImpl(HasNext<Double, DoubleStream> previous, DoubleToIntFunction mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public DoubleToIntFunction getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Integer, IntStream, Q, QS> next) {
        // If the next builder is a LimitBuilder, flip our positions so that it
        // is executed first.
        return next.ifLimit().map(limit -> {

            final LimitAction<Double, DoubleStream> newLimit = LimitAction.create(previous(), limit.getLimit());
            final HasNext<Double, DoubleStream> newPrevious = previous().append(newLimit);
            final MapDoubleToIntAction newMap = MapDoubleToIntAction.create(newPrevious, mapper);
            
            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newLimit.append(newMap);
            
            return result;
            
        // If the next builder is a SkipBuilder, flip our positions so that it
        // is executed first.
        }).orElseGet(() -> next.ifSkip().map(skip -> {

            final SkipAction<Double, DoubleStream> newSkip = SkipAction.create(previous(), skip.getSkip());
            final HasNext<Double, DoubleStream> newPrevious = previous().append(newSkip);
            final MapDoubleToIntAction newMap = MapDoubleToIntAction.create(newPrevious, mapper);

            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newSkip.append(newMap);

            return result;
            
        // The next builder is not either skip or limit. Return it so that the
        // order is preserved.
        }).orElse(next));
    }

    @Override
    public IntStream build(boolean parallel) {
        return previous().build(parallel).mapToInt(mapper);
    }
}