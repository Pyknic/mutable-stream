package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ToIntArrayTerminator;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ToIntArrayTerminatorImpl
extends AbstractTerminator<Integer, IntStream, int[]> 
implements ToIntArrayTerminator {
    
    public ToIntArrayTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public int[] execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.toArray();
        }
    }
}