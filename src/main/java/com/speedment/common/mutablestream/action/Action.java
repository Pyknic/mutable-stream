package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.internal.util.CastUtil;
import java.util.Optional;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.HasPrevious;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the ingoing type
 * @param <TS> the ingoing stream type
 * @param <R>  the outgoing type
 * @param <RS> the outgoing stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface Action<
    T, TS extends BaseStream<T, TS>, 
    R, RS extends BaseStream<R, RS>
> extends HasPrevious<T, TS>, HasNext<R, RS> {
 
    @SuppressWarnings("unchecked")
    default Optional<FilterAction<T>> ifFilter() {
        return CastUtil.castIf(this, FilterAction.class)
            .map(a -> (FilterAction<T>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<MapAction<T, R>> ifMap() {
        return CastUtil.castIf(this, MapAction.class)
            .map(a -> (MapAction<T, R>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<MapToIntAction<T>> ifMapToInt() {
        return CastUtil.castIf(this, MapToIntAction.class)
            .map(a -> (MapToIntAction<T>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<FlatMapAction<T, R>> ifFlatMap() {
        return CastUtil.castIf(this, FlatMapAction.class)
            .map(a -> (FlatMapAction<T, R>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<FlatMapToIntAction<T>> ifFlatMapToInt() {
        return CastUtil.castIf(this, FlatMapToIntAction.class)
            .map(a -> (FlatMapToIntAction<T>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<LimitAction<T, TS>> ifLimit() {
        return CastUtil.castIf(this, LimitAction.class)
            .map(a -> (LimitAction<T, TS>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<SkipAction<T, TS>> ifSkip() {
        return CastUtil.castIf(this, SkipAction.class)
            .map(a -> (SkipAction<T, TS>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<DistinctAction<T, TS>> ifDistinct() {
        return CastUtil.castIf(this, DistinctAction.class)
            .map(a -> (DistinctAction<T, TS>) a);
    }
    
    @SuppressWarnings("unchecked")
    default Optional<SortedAction<T, TS>> ifSorted() {
        return CastUtil.castIf(this, SortedAction.class)
            .map(a -> (SortedAction<T, TS>) a);
    }
}