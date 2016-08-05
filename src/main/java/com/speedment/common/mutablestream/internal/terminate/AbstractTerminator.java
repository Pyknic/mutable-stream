package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.Terminator;
import static java.util.Objects.requireNonNull;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the terminated stream type
 * @param <R>  the final type returned upon execution
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
abstract class AbstractTerminator<T, TS extends BaseStream<T, TS>, R> implements Terminator<T, TS, R> {
    
    private final HasNext<T, TS> previous;
    
    protected AbstractTerminator(HasNext<T, TS> previous) {
        this.previous = requireNonNull(previous);
    }
    
    @Override
    public final HasNext<T, TS> previous() {
        return previous;
    }
}