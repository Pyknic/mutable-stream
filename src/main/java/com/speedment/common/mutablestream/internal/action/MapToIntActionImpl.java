package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapToIntAction;
import com.speedment.common.mutablestream.action.SkipAction;
import static java.util.Objects.requireNonNull;
import java.util.OptionalInt;
import java.util.function.ToIntFunction;
import java.util.stream.BaseStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the ingoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MapToIntActionImpl<T> 
extends AbstractAction<T, Stream<T>, Integer, IntStream> 
implements MapToIntAction<T> {
    
    private final ToIntFunction<T> mapper;

    public MapToIntActionImpl(HasNext<T, Stream<T>> previous, ToIntFunction<T> mapper) {
        super(previous);
        this.mapper = requireNonNull(mapper);
    }
    
    @Override
    public ToIntFunction<T> getMapper() {
        return mapper;
    }

    @Override
    public OptionalInt count() {
        // Mapping does not affect the count.
        return previous().count();
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Integer, IntStream, Q, QS> next) {
        // If the next builder is a LimitBuilder, flip our positions so that it
        // is executed first.
        return next.ifLimit().map(limit -> {
            
            final LimitAction<T, Stream<T>> newLimit = LimitAction.create(previous(), limit.getLimit());
            final MapToIntAction<T> newMap = MapToIntAction.create(newLimit, mapper);
            
            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newLimit.append(newMap);
            
            return result;
            
        // If the next builder is a SkipBuilder, flip our positions so that it
        // is executed first.
        }).orElseGet(() -> next.ifSkip().map(skip -> {
            
            final SkipAction<T, Stream<T>> newSkip = SkipAction.create(previous(), skip.getSkip());
            final MapToIntAction<T> newMap = MapToIntAction.create(newSkip, mapper);

            @SuppressWarnings("unchecked")
            final HasNext<Q, QS> result = (HasNext<Q, QS>) newSkip.append(newMap);

            return result;
            
        // The next builder is not either skip or limit. Return it so that the
        // order is preserved.
        }).orElse(next));
    }

    @Override
    public IntStream build() {
        return previous().build().mapToInt(mapper);
    }
}