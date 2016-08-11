package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapIntToLongAction;
import com.speedment.common.mutablestream.action.SkipAction;
import java.util.stream.BaseStream;
import java.util.stream.IntStream;
import static java.util.Objects.requireNonNull;
import java.util.function.IntToLongFunction;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MapIntToLongActionImpl
extends AbstractAction<Integer, IntStream, Long, LongStream> 
implements MapIntToLongAction {
    
    private final IntToLongFunction mapper;

    public MapIntToLongActionImpl(HasNext<Integer, IntStream> previous, IntToLongFunction mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public IntToLongFunction getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Long, LongStream, Q, QS> next) {
        // If the next builder is a LimitBuilder, flip our positions so that it
        // is executed first.
        return next.ifLimit().map(limit -> {

            final LimitAction<Integer, IntStream> newLimit = LimitAction.create(previous(), limit.getLimit());
            final HasNext<Integer, IntStream> newPrevious = previous().append(newLimit);
            final MapIntToLongAction newMap = MapIntToLongAction.create(newPrevious, mapper);
            
            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newLimit.append(newMap);
            
            return result;
            
        // If the next builder is a SkipBuilder, flip our positions so that it
        // is executed first.
        }).orElseGet(() -> next.ifSkip().map(skip -> {

            final SkipAction<Integer, IntStream> newSkip = SkipAction.create(previous(), skip.getSkip());
            final HasNext<Integer, IntStream> newPrevious = previous().append(newSkip);
            final MapIntToLongAction newMap = MapIntToLongAction.create(newPrevious, mapper);

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