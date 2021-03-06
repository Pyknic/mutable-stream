package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapLongToLongAction;
import com.speedment.common.mutablestream.action.SkipAction;
import java.util.function.LongUnaryOperator;
import java.util.stream.BaseStream;
import java.util.stream.LongStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MapLongToLongActionImpl
extends AbstractAction<Long, LongStream, Long, LongStream> 
implements MapLongToLongAction {
    
    private final LongUnaryOperator mapper;

    public MapLongToLongActionImpl(HasNext<Long, LongStream> previous, LongUnaryOperator mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public LongUnaryOperator getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Long, LongStream, Q, QS> next) {
        // If the next builder is a LimitBuilder, flip our positions so that it
        // is executed first.
        return next.ifLimit().map(limit -> {

            final LimitAction<Long, LongStream> newLimit = LimitAction.create(previous(), limit.getLimit());
            final HasNext<Long, LongStream> newPrevious = previous().append(newLimit);
            final MapLongToLongAction newMap = MapLongToLongAction.create(newPrevious, mapper);
            
            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newLimit.append(newMap);
            
            return result;
            
        // If the next builder is a SkipBuilder, flip our positions so that it
        // is executed first.
        }).orElseGet(() -> next.ifSkip().map(skip -> {

            final SkipAction<Long, LongStream> newSkip = SkipAction.create(previous(), skip.getSkip());
            final HasNext<Long, LongStream> newPrevious = previous().append(newSkip);
            final MapLongToLongAction newMap = MapLongToLongAction.create(newPrevious, mapper);

            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newSkip.append(newMap);

            return result;
            
        // The next builder is not either skip or limit. Return it so that the
        // order is preserved.
        }).orElse(next));
    }

    @Override
    public LongStream build(boolean parallel) {
        return previous().build(parallel).map(mapper);
    }
}