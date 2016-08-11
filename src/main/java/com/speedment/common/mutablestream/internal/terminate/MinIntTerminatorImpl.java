package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.MinIntTerminator;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MinIntTerminatorImpl 
extends AbstractTerminator<Integer, IntStream, OptionalInt>
implements MinIntTerminator {

    public MinIntTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public OptionalInt execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.min();
        }
    }
}