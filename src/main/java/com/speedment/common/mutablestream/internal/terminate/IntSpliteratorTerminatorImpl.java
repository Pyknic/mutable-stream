package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.IntSpliteratorTerminator;
import java.util.Spliterator;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class IntSpliteratorTerminatorImpl 
extends AbstractTerminator<Integer, IntStream, Spliterator.OfInt>
implements IntSpliteratorTerminator {

    public IntSpliteratorTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public Spliterator.OfInt execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.spliterator();
        }
    }
}