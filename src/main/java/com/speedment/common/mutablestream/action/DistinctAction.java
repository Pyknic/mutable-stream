package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.DistinctActionImpl;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the streamed type
 * @param <TS> the main stream interface
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface DistinctAction<T, TS extends BaseStream<T, TS>> 
extends Action<T, TS, T, TS> {
    
    static <T, TS extends BaseStream<T, TS>> DistinctAction<T, TS> create(HasNext<T, TS> previous) {
        return new DistinctActionImpl<>(previous);
    }
    
}
