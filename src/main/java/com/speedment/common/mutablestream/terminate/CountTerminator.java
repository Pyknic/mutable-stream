package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.CountTerminatorImpl;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the terminated stream type
 * @param <TS> the main stream interface
 * 
 * @author Emil Forslund
 * @since   1.0.0
 */
public interface CountTerminator<T, TS extends BaseStream<T, TS>> extends Terminator<T, TS, Long> {
    
    static <T, TS extends BaseStream<T, TS>> CountTerminator<T, TS> create(HasNext<T, TS> previous) {
        return new CountTerminatorImpl<>(previous);
    }
}