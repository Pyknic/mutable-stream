package com.speedment.common.mutablestream;

import com.speedment.common.mutablestream.action.DistinctAction;
import com.speedment.common.mutablestream.action.FlatMapLongAction;
import com.speedment.common.mutablestream.action.LongFilterAction;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapLongAction;
import com.speedment.common.mutablestream.action.MapLongToDoubleAction;
import com.speedment.common.mutablestream.action.MapLongToIntAction;
import com.speedment.common.mutablestream.action.MapLongToLongAction;
import com.speedment.common.mutablestream.action.SkipAction;
import com.speedment.common.mutablestream.action.SortedAction;
import com.speedment.common.mutablestream.terminate.AllMatchLongTerminator;
import com.speedment.common.mutablestream.terminate.AnyMatchLongTerminator;
import com.speedment.common.mutablestream.terminate.AverageTerminator;
import com.speedment.common.mutablestream.terminate.CollectLongTerminator;
import com.speedment.common.mutablestream.terminate.CountTerminator;
import com.speedment.common.mutablestream.terminate.FindAnyLongTerminator;
import com.speedment.common.mutablestream.terminate.FindFirstLongTerminator;
import com.speedment.common.mutablestream.terminate.ForEachLongOrderedTerminator;
import com.speedment.common.mutablestream.terminate.ForEachLongTerminator;
import com.speedment.common.mutablestream.terminate.LongIteratorTerminator;
import com.speedment.common.mutablestream.terminate.LongSpliteratorTerminator;
import com.speedment.common.mutablestream.terminate.LongSummaryStatisticsTerminator;
import com.speedment.common.mutablestream.terminate.MaxLongTerminator;
import com.speedment.common.mutablestream.terminate.MinLongTerminator;
import com.speedment.common.mutablestream.terminate.NoneMatchLongTerminator;
import com.speedment.common.mutablestream.terminate.ReduceLongTerminator;
import com.speedment.common.mutablestream.terminate.ToLongArrayTerminator;
import java.util.LongSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.terminate.SumLongTerminator;
import java.util.function.LongToIntFunction;
import java.util.stream.IntStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Emil Forslund
 * @since   1.0.0
 */
public final class MutableLongStream implements LongStream {
    
    public static LongStream wrap(HasNext<Long, LongStream> pipeline) {
        return internalWrap(pipeline, false);
    }
    
    static LongStream internalWrap(HasNext<Long, LongStream> pipeline, boolean parallel) {
        return new MutableLongStream(pipeline, parallel);
    }
    
    /**************************************************************************/
    /*                          Intermediate Actions                          */
    /**************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream filter(LongPredicate filter) {
        return internalWrap(pipeline.append(LongFilterAction.create(pipeline, filter)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream map(LongUnaryOperator mapper) {
        return internalWrap(pipeline.append(MapLongToLongAction.create(pipeline, mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
        return MutableStream.internalWrap(pipeline.append(MapLongAction.create(pipeline, (LongFunction<U>) mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream mapToInt(LongToIntFunction mapper) {
        return MutableIntStream.internalWrap(pipeline.append(MapLongToIntAction.create(pipeline, mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream mapToDouble(LongToDoubleFunction mapper) {
        return MutableDoubleStream.internalWrap(pipeline.append(MapLongToDoubleAction.create(pipeline, mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public LongStream flatMap(LongFunction<? extends LongStream> mapper) {
        return internalWrap(pipeline.append(FlatMapLongAction.create(pipeline, (LongFunction<LongStream>) mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream distinct() {
        return internalWrap(pipeline.append(DistinctAction.create(pipeline)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream sorted() {
        return internalWrap(pipeline.append(SortedAction.create(pipeline)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream peek(LongConsumer ic) {
        // Mutable Streams can not be peeked inside since they might not be
        // resolved as a stream at all.
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream limit(long limit) {
        return internalWrap(pipeline.append(LimitAction.create(pipeline, limit)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream skip(long skip) {
        return internalWrap(pipeline.append(SkipAction.create(pipeline, skip)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream asDoubleStream() {
        return mapToDouble(i -> i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Long> boxed() {
        return mapToObj(i -> i);
    }
    
    /**************************************************************************/
    /*                          Terminating Actions                           */
    /**************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(LongConsumer action) {
        pipeline.execute(ForEachLongTerminator.create(pipeline, parallel, action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEachOrdered(LongConsumer action) {
        pipeline.execute(ForEachLongOrderedTerminator.create(pipeline, parallel, action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] toArray() {
        return pipeline.execute(ToLongArrayTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long reduce(long initialValue, LongBinaryOperator combiner) {
        return pipeline.execute(ReduceLongTerminator.create(pipeline, parallel, initialValue, combiner)).getAsLong();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalLong reduce(LongBinaryOperator combiner) {
        return pipeline.execute(ReduceLongTerminator.create(pipeline, parallel, combiner));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R, R> merger) {
        return pipeline.execute(CollectLongTerminator.create(pipeline, parallel, supplier, accumulator, merger));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long sum() {
        return pipeline.execute(SumLongTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalLong min() {
        return pipeline.execute(MinLongTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalLong max() {
        return pipeline.execute(MaxLongTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return pipeline.execute(CountTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalDouble average() {
        return pipeline.execute(AverageTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongSummaryStatistics summaryStatistics() {
        return pipeline.execute(LongSummaryStatisticsTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean anyMatch(LongPredicate predicate) {
        return pipeline.execute(AnyMatchLongTerminator.create(pipeline, parallel, predicate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean allMatch(LongPredicate predicate) {
        return pipeline.execute(AllMatchLongTerminator.create(pipeline, parallel, predicate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean noneMatch(LongPredicate predicate) {
        return pipeline.execute(NoneMatchLongTerminator.create(pipeline, parallel, predicate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalLong findFirst() {
        return pipeline.execute(FindFirstLongTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalLong findAny() {
        return pipeline.execute(FindAnyLongTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PrimitiveIterator.OfLong iterator() {
        return pipeline.execute(LongIteratorTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Spliterator.OfLong spliterator() {
        return pipeline.execute(LongSpliteratorTerminator.create(pipeline, parallel));
    }

    
    /**************************************************************************/
    /*                   Inherited Methods from Base Stream                   */
    /**************************************************************************/
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParallel() {
        return parallel;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream sequential() {
        return parallel ? internalWrap(pipeline, false) : this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream parallel() {
        return parallel ? this : internalWrap(pipeline, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream unordered() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream onClose(Runnable r) {
        throw new UnsupportedOperationException(
            "Close listeners are not supported by this stream implementation."
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        // Do nothing since close listeners are not supported by this 
        // implementation of the stream API.
    }
    
    /**************************************************************************/
    /*                             Constructor                                */
    /**************************************************************************/
    
    private MutableLongStream(HasNext<Long, LongStream> pipeline, boolean parallel) {
        this.pipeline = requireNonNull(pipeline);
        this.parallel = parallel;
    }
    
    private final HasNext<Long, LongStream> pipeline;
    private final boolean parallel;
}
