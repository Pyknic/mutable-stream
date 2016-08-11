package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import java.util.stream.LongStream;
import com.speedment.common.mutablestream.terminate.SumLongTerminator;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class SumLongTerminatorImpl
extends AbstractTerminator<Long, LongStream, Long> 
implements SumLongTerminator {
    
    public SumLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.sum();
        }
    }
}