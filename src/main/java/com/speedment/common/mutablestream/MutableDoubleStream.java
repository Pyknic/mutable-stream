package com.speedment.common.mutablestream;

import com.speedment.common.mutablestream.action.DistinctAction;
import com.speedment.common.mutablestream.action.FlatMapDoubleAction;
import com.speedment.common.mutablestream.action.DoubleFilterAction;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapDoubleAction;
import com.speedment.common.mutablestream.action.MapDoubleToDoubleAction;
import com.speedment.common.mutablestream.action.MapDoubleToIntAction;
import com.speedment.common.mutablestream.action.MapDoubleToLongAction;
import com.speedment.common.mutablestream.action.SkipAction;
import com.speedment.common.mutablestream.action.SortedAction;
import com.speedment.common.mutablestream.terminate.AllMatchDoubleTerminator;
import com.speedment.common.mutablestream.terminate.AnyMatchDoubleTerminator;
import com.speedment.common.mutablestream.terminate.AverageTerminator;
import com.speedment.common.mutablestream.terminate.CollectDoubleTerminator;
import com.speedment.common.mutablestream.terminate.CountTerminator;
import com.speedment.common.mutablestream.terminate.FindAnyDoubleTerminator;
import com.speedment.common.mutablestream.terminate.FindFirstDoubleTerminator;
import com.speedment.common.mutablestream.terminate.ForEachDoubleOrderedTerminator;
import com.speedment.common.mutablestream.terminate.ForEachDoubleTerminator;
import com.speedment.common.mutablestream.terminate.DoubleIteratorTerminator;
import com.speedment.common.mutablestream.terminate.DoubleSpliteratorTerminator;
import com.speedment.common.mutablestream.terminate.DoubleSummaryStatisticsTerminator;
import com.speedment.common.mutablestream.terminate.MaxDoubleTerminator;
import com.speedment.common.mutablestream.terminate.MinDoubleTerminator;
import com.speedment.common.mutablestream.terminate.NoneMatchDoubleTerminator;
import com.speedment.common.mutablestream.terminate.ReduceDoubleTerminator;
import com.speedment.common.mutablestream.terminate.ToDoubleArrayTerminator;
import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.terminate.SumDoubleTerminator;
import java.util.function.DoubleToIntFunction;
import java.util.stream.IntStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Emil Forslund
 * @since   1.0.0
 */
public final class MutableDoubleStream implements DoubleStream {
    
    public static DoubleStream wrap(HasNext<Double, DoubleStream> pipeline) {
        return internalWrap(pipeline, false);
    }
    
    static DoubleStream internalWrap(HasNext<Double, DoubleStream> pipeline, boolean parallel) {
        return new MutableDoubleStream(pipeline, parallel);
    }
    
    /**************************************************************************/
    /*                          Doubleermediate Actions                          */
    /**************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream filter(DoublePredicate filter) {
        return internalWrap(pipeline.append(DoubleFilterAction.create(pipeline, filter)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream map(DoubleUnaryOperator mapper) {
        return internalWrap(pipeline.append(MapDoubleToDoubleAction.create(pipeline, mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return MutableStream.internalWrap(pipeline.append(MapDoubleAction.create(pipeline, (DoubleFunction<U>) mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream mapToLong(DoubleToLongFunction mapper) {
        return MutableLongStream.internalWrap(pipeline.append(MapDoubleToLongAction.create(pipeline, mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream mapToInt(DoubleToIntFunction mapper) {
        return MutableIntStream.internalWrap(pipeline.append(MapDoubleToIntAction.create(pipeline, mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper) {
        return internalWrap(pipeline.append(FlatMapDoubleAction.create(pipeline, (DoubleFunction<DoubleStream>) mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream distinct() {
        return internalWrap(pipeline.append(DistinctAction.create(pipeline)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream sorted() {
        return internalWrap(pipeline.append(SortedAction.create(pipeline)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream peek(DoubleConsumer ic) {
        // Mutable Streams can not be peeked inside since they might not be
        // resolved as a stream at all.
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream limit(long limit) {
        return internalWrap(pipeline.append(LimitAction.create(pipeline, limit)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream skip(long skip) {
        return internalWrap(pipeline.append(SkipAction.create(pipeline, skip)), parallel);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Double> boxed() {
        return mapToObj(i -> i);
    }
    
    /**************************************************************************/
    /*                          Terminating Actions                           */
    /**************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(DoubleConsumer action) {
        pipeline.execute(ForEachDoubleTerminator.create(pipeline, parallel, action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEachOrdered(DoubleConsumer action) {
        pipeline.execute(ForEachDoubleOrderedTerminator.create(pipeline, parallel, action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return pipeline.execute(ToDoubleArrayTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double reduce(double initialValue, DoubleBinaryOperator combiner) {
        return pipeline.execute(ReduceDoubleTerminator.create(pipeline, parallel, initialValue, combiner)).getAsDouble();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalDouble reduce(DoubleBinaryOperator combiner) {
        return pipeline.execute(ReduceDoubleTerminator.create(pipeline, parallel, combiner));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R, R> merger) {
        return pipeline.execute(CollectDoubleTerminator.create(pipeline, parallel, supplier, accumulator, merger));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sum() {
        return pipeline.execute(SumDoubleTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalDouble min() {
        return pipeline.execute(MinDoubleTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalDouble max() {
        return pipeline.execute(MaxDoubleTerminator.create(pipeline, parallel));
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
    public DoubleSummaryStatistics summaryStatistics() {
        return pipeline.execute(DoubleSummaryStatisticsTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean anyMatch(DoublePredicate predicate) {
        return pipeline.execute(AnyMatchDoubleTerminator.create(pipeline, parallel, predicate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean allMatch(DoublePredicate predicate) {
        return pipeline.execute(AllMatchDoubleTerminator.create(pipeline, parallel, predicate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean noneMatch(DoublePredicate predicate) {
        return pipeline.execute(NoneMatchDoubleTerminator.create(pipeline, parallel, predicate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalDouble findFirst() {
        return pipeline.execute(FindFirstDoubleTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalDouble findAny() {
        return pipeline.execute(FindAnyDoubleTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PrimitiveIterator.OfDouble iterator() {
        return pipeline.execute(DoubleIteratorTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Spliterator.OfDouble spliterator() {
        return pipeline.execute(DoubleSpliteratorTerminator.create(pipeline, parallel));
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
    public DoubleStream sequential() {
        return parallel ? internalWrap(pipeline, false) : this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream parallel() {
        return parallel ? this : internalWrap(pipeline, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream unordered() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream onClose(Runnable r) {
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
    
    private MutableDoubleStream(HasNext<Double, DoubleStream> pipeline, boolean parallel) {
        this.pipeline = requireNonNull(pipeline);
        this.parallel = parallel;
    }
    
    private final HasNext<Double, DoubleStream> pipeline;
    private final boolean parallel;
}
