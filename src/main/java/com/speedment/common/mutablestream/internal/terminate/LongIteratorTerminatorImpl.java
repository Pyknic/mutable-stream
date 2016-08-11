package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.LongIteratorTerminator;
import java.util.PrimitiveIterator;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class LongIteratorTerminatorImpl
extends AbstractTerminator<Long, LongStream, PrimitiveIterator.OfLong> 
implements LongIteratorTerminator {

    public LongIteratorTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public PrimitiveIterator.OfLong execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.iterator();
        }
    }
}