package com.speedment.common.mutablestream.source;

import com.speedment.common.mutablestream.internal.source.EmptySourceImpl;
import com.speedment.common.mutablestream.HasNext;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the streamed type
 * @param <TS> the type of the stream itself
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface EmptySource<T, TS extends BaseStream<T, TS>> extends HasNext<T, TS> {
    
    static <T, TS extends BaseStream<T, TS>> EmptySource<T, TS> create() {
        return new EmptySourceImpl<>();
    }
    
}