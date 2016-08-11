package com.speedment.common.mutablestream.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.terminate.DoubleSummaryStatisticsTerminatorImpl;
import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface DoubleSummaryStatisticsTerminator 
extends Terminator<Double, DoubleStream, DoubleSummaryStatistics> {
    
    static DoubleSummaryStatisticsTerminator create(HasNext<Double, DoubleStream> previous, boolean parallel) {
        return new DoubleSummaryStatisticsTerminatorImpl(previous, parallel);
    }
}