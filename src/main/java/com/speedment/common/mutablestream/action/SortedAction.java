package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.SortedActionImpl;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>   the streamed type
 * @param <TS>  the type of the stream itself
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface SortedAction<T, TS extends BaseStream<T, TS>> extends Action<T, TS, T, TS> {
    
    Optional<Comparator<T>> getComparator();
    
    static <T, TS extends BaseStream<T, TS>> SortedAction<T, TS> create(HasNext<T, TS> previous) {
        return create(previous, null);
    }
    
    static <T, TS extends BaseStream<T, TS>> SortedAction<T, TS> create(HasNext<T, TS> previous, Comparator<T> comparator) {
        return new SortedActionImpl<>(previous, comparator);
    }
}