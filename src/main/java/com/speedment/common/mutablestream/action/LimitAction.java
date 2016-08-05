package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.internal.action.LimitActionImpl;
import com.speedment.common.mutablestream.HasNext;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the streamed type
 * @param <TS> the main stream interface
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface LimitAction<T, TS extends BaseStream<T, TS>> extends Action<T, TS, T, TS> {

    long getLimit();
    
    static <T, TS extends BaseStream<T, TS>> LimitAction<T, TS> create(HasNext<T, TS> previous, long limit) {
        return new LimitActionImpl<>(previous, limit);
    }
}