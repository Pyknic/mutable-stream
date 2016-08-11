package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.IntSummaryStatisticsTerminatorImpl;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface IntSummaryStatisticsTerminator 
extends Terminator<Integer, IntStream, IntSummaryStatistics> {
    
    static IntSummaryStatisticsTerminator create(HasNext<Integer, IntStream> previous, boolean parallel) {
        return new IntSummaryStatisticsTerminatorImpl(previous, parallel);
    }
}