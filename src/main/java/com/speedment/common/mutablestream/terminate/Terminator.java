package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasPrevious;
import java.util.stream.BaseStream;

/**
 *
 * @param <T>   the terminated stream type
 * @param <TS>  the type of the stream itself
 * @param <R>   the final type returned upon execution
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface Terminator<T, TS extends BaseStream<T, TS>, R> extends HasPrevious<T, TS> {

    boolean isParallel();
    
    R execute();
    
}