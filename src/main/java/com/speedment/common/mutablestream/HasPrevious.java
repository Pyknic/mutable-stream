package com.speedment.common.mutablestream;

import java.util.stream.BaseStream;

/**
 *
 * @param <T>   the ingoing type
 * @param <TS>  the ingoing stream type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface HasPrevious<T, TS extends BaseStream<T, TS>> {
    
    HasNext<T, TS> previous();
    
}