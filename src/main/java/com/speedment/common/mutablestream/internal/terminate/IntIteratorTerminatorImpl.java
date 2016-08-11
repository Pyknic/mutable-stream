package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.IntIteratorTerminator;
import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class IntIteratorTerminatorImpl
extends AbstractTerminator<Integer, IntStream, PrimitiveIterator.OfInt> 
implements IntIteratorTerminator {

    public IntIteratorTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public PrimitiveIterator.OfInt execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.iterator();
        }
    }
}