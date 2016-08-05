package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import static java.util.Objects.requireNonNull;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>  the ingoing type
 * @param <R>  the outgoing type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
abstract class AbstractAction<
    T, TS extends BaseStream<T, TS>, 
    R, RS extends BaseStream<R, RS>
> implements Action<T, TS, R, RS> {
    
    private final HasNext<T, TS> previous;
    
    protected AbstractAction(HasNext<T, TS> previous) {
        this.previous = requireNonNull(previous);
    }
    
    @Override
    public final HasNext<T, TS> previous() {
        return previous;
    }
}