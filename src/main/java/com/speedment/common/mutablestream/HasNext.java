package com.speedment.common.mutablestream;

import java.util.OptionalInt;
import com.speedment.common.mutablestream.action.Action;
import java.util.stream.BaseStream;

/**
 *
 * @param <R>   the outgoing type
 * @param <RS>  the type of the outgoing stream
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface HasNext<R, RS extends BaseStream<R, RS>> {
    
    <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<R, RS, Q, QS> next);
    
    OptionalInt count();
    
    RS build();
    
}