package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.AverageTerminatorImpl;
import java.util.OptionalDouble;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>   the streamed type
 * @param <TS>  the type of the stream itself
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface AverageTerminator<T, TS extends BaseStream<T, TS>> 
extends Terminator<T, TS, OptionalDouble> {
    
    static <T, TS extends BaseStream<T, TS>> AverageTerminator<T, TS> 
    create(HasNext<T, TS> previous, boolean parallel) {
        
        return new AverageTerminatorImpl<>(previous, parallel);
    }
    
}