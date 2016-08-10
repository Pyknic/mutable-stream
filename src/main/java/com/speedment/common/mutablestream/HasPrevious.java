package com.speedment.common.mutablestream;

import java.util.stream.BaseStream;

/**
 * A pipeline action that is supposed to follow a previous action. This can be
 * either an intermediary or a terminating action.
 * <p>
 * Implementations of this interface should be <em>immutable</em> and contain
 * publically accessible getters for all the metadata nescessary to execute it.
 * 
 * @param <T>   the ingoing type
 * @param <TS>  the ingoing stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface HasPrevious<T, TS extends BaseStream<T, TS>> {
    
    /**
     * Returns a reference to the previous action.
     * 
     * @return  the previous action
     */
    HasNext<T, TS> previous();
    
}