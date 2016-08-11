package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.LongSpliteratorTerminator;
import java.util.Spliterator;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class LongSpliteratorTerminatorImpl 
extends AbstractTerminator<Long, LongStream, Spliterator.OfLong>
implements LongSpliteratorTerminator {

    public LongSpliteratorTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public Spliterator.OfLong execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.spliterator();
        }
    }
}