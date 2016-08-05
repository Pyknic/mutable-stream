package com.speedment.common.mutablestream.internal.action;

import java.util.function.Function;
import java.util.OptionalInt;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapAction;
import com.speedment.common.mutablestream.action.SkipAction;
import static java.util.Objects.requireNonNull;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the ingoing type
 * @param <R>  the outgoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MapActionImpl<T, R> 
extends AbstractAction<T, Stream<T>, R, Stream<R>> 
implements MapAction<T, R> {
    
    private final Function<T, R> mapper;

    public MapActionImpl(HasNext<T, Stream<T>> previous, Function<T, R> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public Function<T, R> getMapper() {
        return mapper;
    }

    @Override
    public OptionalInt count() {
        // Mapping does not affect the count.
        return previous().count();
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<R, Stream<R>, Q, QS> next) {
        // If the next action is a LimitAction, flip our positions so that it
        // is executed first.
        return next.ifLimit().map(limit -> {
            
            final LimitAction<T, Stream<T>> newLimit = LimitAction.create(previous(), limit.getLimit());
            final HasNext<T, Stream<T>> newPrevious = previous().append(newLimit);
            final MapAction<T, R> newMap = MapAction.create(newPrevious, mapper);
            
            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newPrevious.append(newMap);
            
            return result;
            
        // If the next action is a SkipAction, flip our positions so that it
        // is executed first.
        }).orElseGet(() -> next.ifSkip().map(skip -> {
            
            final SkipAction<T, Stream<T>> newSkip = SkipAction.create(previous(), skip.getSkip());
            final HasNext<T, Stream<T>> newPrevious = previous().append(newSkip);
            final MapAction<T, R> newMap = MapAction.create(newPrevious, mapper);

            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newPrevious.append(newMap);

            return result;

        // The next action is not either skip or limit. Return it so that the 
        // order is preserved.
        }).orElse(next));
    }

    @Override
    public Stream<R> build() {
        return previous().build().map(mapper);
    }
}