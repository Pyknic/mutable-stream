package com.speedment.common.mutablestream;

import com.speedment.common.mutablestream.action.DistinctAction;
import com.speedment.common.mutablestream.action.FlatMapIntAction;
import com.speedment.common.mutablestream.action.IntFilterAction;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapIntAction;
import com.speedment.common.mutablestream.action.MapIntToIntAction;
import com.speedment.common.mutablestream.action.SkipAction;
import com.speedment.common.mutablestream.action.SortedAction;
import com.speedment.common.mutablestream.terminate.CountTerminator;
import com.speedment.common.mutablestream.terminate.ForEachIntOrderedTerminator;
import com.speedment.common.mutablestream.terminate.ForEachIntTerminator;
import com.speedment.common.mutablestream.terminate.ReduceIntTerminator;
import com.speedment.common.mutablestream.terminate.ToIntArrayTerminator;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Emil Forslund
 * @since   1.0.0
 */
public final class MutableIntStream implements IntStream {
    
    public static IntStream wrap(HasNext<Integer, IntStream> pipeline) {
        return wrap(pipeline, false);
    }
    
    static IntStream wrap(HasNext<Integer, IntStream> pipeline, boolean parallel) {
        return new MutableIntStream(pipeline, parallel);
    }
    
    /**************************************************************************/
    /*                          Intermediate Actions                          */
    /**************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream filter(IntPredicate filter) {
        return wrap(pipeline.append(IntFilterAction.create(pipeline, filter)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return wrap(pipeline.append(MapIntToIntAction.create(pipeline, mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
        return MutableStream.wrap(pipeline.append(MapIntAction.create(pipeline, (IntFunction<U>) mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream mapToLong(IntToLongFunction mapper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
        return wrap(pipeline.append(FlatMapIntAction.create(pipeline, (IntFunction<IntStream>) mapper)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream distinct() {
        return wrap(pipeline.append(DistinctAction.create(pipeline)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream sorted() {
        return wrap(pipeline.append(SortedAction.create(pipeline)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream peek(IntConsumer ic) {
        // Mutable Streams can not be peeked inside since they might not be
        // resolved as a stream at all.
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream limit(long limit) {
        return wrap(pipeline.append(LimitAction.create(pipeline, limit)), parallel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream skip(long skip) {
        return wrap(pipeline.append(SkipAction.create(pipeline, skip)), parallel);
    }
    
    /**************************************************************************/
    /*                          Terminating Actions                           */
    /**************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(IntConsumer action) {
        pipeline.execute(ForEachIntTerminator.create(pipeline, parallel, action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEachOrdered(IntConsumer action) {
        pipeline.execute(ForEachIntOrderedTerminator.create(pipeline, parallel, action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] toArray() {
        return pipeline.execute(ToIntArrayTerminator.create(pipeline, parallel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int reduce(int initialValue, IntBinaryOperator combiner) {
        return pipeline.execute(ReduceIntTerminator.create(pipeline, parallel, initialValue, combiner)).getAsInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt reduce(IntBinaryOperator combiner) {
        return pipeline.execute(ReduceIntTerminator.create(pipeline, parallel, combiner));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R collect(Supplier<R> splr, ObjIntConsumer<R> oic, BiConsumer<R, R> bc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt min() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt max() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntSummaryStatistics summaryStatistics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean anyMatch(IntPredicate ip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean allMatch(IntPredicate ip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean noneMatch(IntPredicate ip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt findFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionalInt findAny() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream asLongStream() {
        return mapToLong(i -> i);
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
    public Stream<Integer> boxed() {
        return mapToObj(i -> i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PrimitiveIterator.OfInt iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Spliterator.OfInt spliterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public IntStream sequential() {
        return parallel ? wrap(pipeline, false) : this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream parallel() {
        return parallel ? this : wrap(pipeline, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream unordered() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntStream onClose(Runnable r) {
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
    
    private MutableIntStream(HasNext<Integer, IntStream> pipeline, boolean parallel) {
        this.pipeline = requireNonNull(pipeline);
        this.parallel = parallel;
    }
    
    private final HasNext<Integer, IntStream> pipeline;
    private final boolean parallel;
}
