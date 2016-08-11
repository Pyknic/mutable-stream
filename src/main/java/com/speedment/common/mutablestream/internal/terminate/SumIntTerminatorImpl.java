package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import java.util.stream.IntStream;
import com.speedment.common.mutablestream.terminate.SumIntTerminator;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class SumIntTerminatorImpl
extends AbstractTerminator<Integer, IntStream, Integer> 
implements SumIntTerminator {
    
    public SumIntTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Integer execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.sum();
        }
    }
}