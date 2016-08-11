package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.MaxIntTerminator;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MaxIntTerminatorImpl 
extends AbstractTerminator<Integer, IntStream, OptionalInt>
implements MaxIntTerminator {

    public MaxIntTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public OptionalInt execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.max();
        }
    }
}