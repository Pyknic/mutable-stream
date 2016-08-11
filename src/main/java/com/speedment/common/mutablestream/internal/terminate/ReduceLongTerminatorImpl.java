package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ReduceLongTerminator;
import static java.util.Objects.requireNonNull;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ReduceLongTerminatorImpl 
extends AbstractTerminator<Long, LongStream, OptionalLong> 
implements ReduceLongTerminator {

    private final long initialValue;
    private final boolean hasInitialValue;
    private final LongBinaryOperator combiner;
    
    public ReduceLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel, LongBinaryOperator combiner) {
        super(previous, parallel);
        this.initialValue    = 0;
        this.hasInitialValue = false;
        this.combiner        = requireNonNull(combiner);
    }

    public ReduceLongTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel, long initialValue, LongBinaryOperator combiner) {
        super(previous, parallel);
        this.initialValue    = initialValue;
        this.hasInitialValue = true;
        this.combiner        = requireNonNull(combiner);
    }
    
    @Override
    public OptionalLong getInitialValue() {
        return hasInitialValue 
            ? OptionalLong.of(initialValue) 
            : OptionalLong.empty();
    }

    @Override
    public LongBinaryOperator getCombiner() {
        return combiner;
    }
    
    @Override
    public OptionalLong execute() {
        try (final LongStream stream = buildPrevious()) {
            if (hasInitialValue) {
                return OptionalLong.of(stream.reduce(initialValue, combiner));
            } else {
                return stream.reduce(combiner);
            }
        }
    }
}
