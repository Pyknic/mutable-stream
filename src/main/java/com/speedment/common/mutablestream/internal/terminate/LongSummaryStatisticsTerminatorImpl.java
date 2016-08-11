package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.LongSummaryStatisticsTerminator;
import java.util.LongSummaryStatistics;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class LongSummaryStatisticsTerminatorImpl 
extends AbstractTerminator<Long, LongStream, LongSummaryStatistics> 
implements LongSummaryStatisticsTerminator {

    public LongSummaryStatisticsTerminatorImpl(HasNext<Long, LongStream> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public LongSummaryStatistics execute() {
        try (final LongStream stream = buildPrevious()) {
            return stream.summaryStatistics();
        }
    }
}