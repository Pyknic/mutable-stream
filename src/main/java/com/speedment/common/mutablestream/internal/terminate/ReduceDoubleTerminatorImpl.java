package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.ReduceDoubleTerminator;
import static java.util.Objects.requireNonNull;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.DoubleStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class ReduceDoubleTerminatorImpl 
extends AbstractTerminator<Double, DoubleStream, OptionalDouble> 
implements ReduceDoubleTerminator {

    private final double initialValue;
    private final boolean hasInitialValue;
    private final DoubleBinaryOperator combiner;
    
    public ReduceDoubleTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel, DoubleBinaryOperator combiner) {
        super(previous, parallel);
        this.initialValue    = 0;
        this.hasInitialValue = false;
        this.combiner        = requireNonNull(combiner);
    }

    public ReduceDoubleTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel, double initialValue, DoubleBinaryOperator combiner) {
        super(previous, parallel);
        this.initialValue    = initialValue;
        this.hasInitialValue = true;
        this.combiner        = requireNonNull(combiner);
    }
    
    @Override
    public OptionalDouble getInitialValue() {
        return hasInitialValue 
            ? OptionalDouble.of(initialValue) 
            : OptionalDouble.empty();
    }

    @Override
    public DoubleBinaryOperator getCombiner() {
        return combiner;
    }
    
    @Override
    public OptionalDouble execute() {
        try (final DoubleStream stream = buildPrevious()) {
            if (hasInitialValue) {
                return OptionalDouble.of(stream.reduce(initialValue, combiner));
            } else {
                return stream.reduce(combiner);
            }
        }
    }
}
