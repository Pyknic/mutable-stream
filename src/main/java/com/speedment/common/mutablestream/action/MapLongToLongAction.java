package com.speedment.common.mutablestream.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.action.MapLongToLongActionImpl;
import java.util.function.LongUnaryOperator;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface MapLongToLongAction extends Action<Long, LongStream, Long, LongStream> {
    
    LongUnaryOperator getMapper();
    
    static MapLongToLongAction create(HasNext<Long, LongStream> previous, LongUnaryOperator mapper) {
        return new MapLongToLongActionImpl(previous, mapper);
    }
}