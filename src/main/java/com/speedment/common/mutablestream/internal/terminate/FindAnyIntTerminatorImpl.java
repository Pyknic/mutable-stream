package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.FindAnyIntTerminator;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FindAnyIntTerminatorImpl
extends AbstractTerminator<Integer, IntStream, OptionalInt> 
implements FindAnyIntTerminator {

    public FindAnyIntTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public OptionalInt execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.findAny();
        }
    }
}