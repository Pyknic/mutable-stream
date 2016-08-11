package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.IntSummaryStatisticsTerminator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class IntSummaryStatisticsTerminatorImpl 
extends AbstractTerminator<Integer, IntStream, IntSummaryStatistics> 
implements IntSummaryStatisticsTerminator {

    public IntSummaryStatisticsTerminatorImpl(HasNext<Integer, IntStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public IntSummaryStatistics execute() {
        try (final IntStream stream = buildPrevious()) {
            return stream.summaryStatistics();
        }
    }
}