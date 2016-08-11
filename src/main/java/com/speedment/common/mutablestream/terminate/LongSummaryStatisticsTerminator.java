package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.LongSummaryStatisticsTerminatorImpl;
import java.util.LongSummaryStatistics;
import java.util.stream.LongStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface LongSummaryStatisticsTerminator 
extends Terminator<Long, LongStream, LongSummaryStatistics> {
    
    static LongSummaryStatisticsTerminator create(HasNext<Long, LongStream> previous, boolean parallel) {
        return new LongSummaryStatisticsTerminatorImpl(previous, parallel);
    }
}