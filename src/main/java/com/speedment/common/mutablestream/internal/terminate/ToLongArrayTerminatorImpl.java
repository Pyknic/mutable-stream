package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ToLongArrayTerminator;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ToLongArrayTerminatorImpl
extends AbstractTerminator<Long, LongStream, long[]> 
implements ToLongArrayTerminator {
    
    public ToLongArrayTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public long[] execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.toArray();
        }
    }
}