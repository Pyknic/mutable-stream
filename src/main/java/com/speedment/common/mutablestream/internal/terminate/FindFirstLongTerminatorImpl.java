package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.FindFirstLongTerminator;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FindFirstLongTerminatorImpl
extends AbstractTerminator<Long, LongStream, OptionalLong> 
implements FindFirstLongTerminator {

    public FindFirstLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public OptionalLong execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.findFirst();
        }
    }
}