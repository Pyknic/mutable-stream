package com.speedment.common.mutablestream.internal.action;

import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapLongAction;
import com.speedment.common.mutablestream.action.SkipAction;
import java.util.function.LongFunction;
import java.util.stream.BaseStream;
import java.util.stream.LongStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <R>  the outgoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MapLongActionImpl<R> 
extends AbstractAction<Long, LongStream, R, Stream<R>> 
implements MapLongAction<R> {
    
    private final LongFunction<R> mapper;

    public MapLongActionImpl(HasNext<Long, LongStream> previous, LongFunction<R> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public LongFunction<R> getMapper() {
        return mapper;
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<R, Stream<R>, Q, QS> next) {
        // If the next action is a LimitAction, flip our positions so that it
        // is executed first.
        return next.ifLimit().map(limit -> {
            
            final LimitAction<Long, LongStream> newLimit = LimitAction.create(previous(), limit.getLimit());
            final HasNext<Long, LongStream> newPrevious = previous().append(newLimit);
            final MapLongAction<R> newMap = MapLongAction.create(newPrevious, mapper);
            
            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newPrevious.append(newMap);
            
            return result;
            
        // If the next action is a SkipAction, flip our positions so that it
        // is executed first.
        }).orElseGet(() -> next.ifSkip().map(skip -> {
            
            final SkipAction<Long, LongStream> newSkip = SkipAction.create(previous(), skip.getSkip());
            final HasNext<Long, LongStream> newPrevious = previous().append(newSkip);
            final MapLongAction<R> newMap = MapLongAction.create(newPrevious, mapper);

            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newPrevious.append(newMap);

            return result;

        // The next action is not either skip or limit. Return it so that the 
        // order is preserved.
        }).orElse(next));
    }

    @Override
    public Stream<R> build(boolean parallel) {
        return previous().build(parallel).mapToObj(mapper);
    }
}