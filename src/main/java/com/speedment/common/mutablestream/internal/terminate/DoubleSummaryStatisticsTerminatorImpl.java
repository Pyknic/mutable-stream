package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.DoubleSummaryStatisticsTerminator;
import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class DoubleSummaryStatisticsTerminatorImpl 
extends AbstractTerminator<Double, DoubleStream, DoubleSummaryStatistics> 
implements DoubleSummaryStatisticsTerminator {

    public DoubleSummaryStatisticsTerminatorImpl(HasNext<Double, DoubleStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public DoubleSummaryStatistics execute() {
        try (final DoubleStream stream = buildPrevious()) {
            return stream.summaryStatistics();
        }
    }
}