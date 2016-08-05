package com.speedment.common.mutablestream;

import com.speedment.common.mutablestream.action.DistinctAction;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.action.FilterAction;
import com.speedment.common.mutablestream.action.FlatMapAction;
import com.speedment.common.mutablestream.action.FlatMapToIntAction;
import com.speedment.common.mutablestream.action.LimitAction;
import com.speedment.common.mutablestream.action.MapAction;
import com.speedment.common.mutablestream.action.MapToIntAction;
import com.speedment.common.mutablestream.action.SkipAction;
import com.speedment.common.mutablestream.action.SortedAction;
import com.speedment.common.mutablestream.terminate.CollectTerminator;
import com.speedment.common.mutablestream.terminate.CountTerminator;
import com.speedment.common.mutablestream.terminate.ForEachTerminator;
import com.speedment.common.mutablestream.terminate.ReduceTerminator;
import com.speedment.common.mutablestream.terminate.ToArrayTerminator;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the type of the stream
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class MutableStream<T> implements Stream<T> {

    public static <T> Stream<T> wrap(HasNext<T, Stream<T>> builder) {
        return new MutableStream<>(builder);
    }
    
    /**************************************************************************/
    /*                          Intermediate Actions                          */
    /**************************************************************************/
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Stream<T> filter(Predicate<? super T> filter) {
        return wrap(builder.append(FilterAction.create(builder, (Predicate<T>) filter)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return wrap(builder.append(MapAction.create(builder, (Function<T, R>) mapper)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return MutableIntStream.wrap(builder.append(MapToIntAction.create(builder, (ToIntFunction<T>) mapper)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream mapToLong(ToLongFunction<? super T> tlf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> tdf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return wrap(builder.append(FlatMapAction.create(builder, (Function<T, Stream<R>>) mapper)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return MutableIntStream.wrap(builder.append(FlatMapToIntAction.create(builder, (Function<T, IntStream>) mapper)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> fnctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> fnctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> distinct() {
        return wrap(builder.append(DistinctAction.create(builder)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> sorted() {
        return wrap(builder.append(SortedAction.create(builder)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return wrap(builder.append(SortedAction.create(builder, (Comparator<T>) comparator)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> peek(Consumer<? super T> cnsmr) {
        // Mutable Streams can not be peeked inside since they might not be
        // resolved as a stream at all.
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> limit(long limit) {
        return wrap(builder.append(LimitAction.create(builder, limit)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> skip(long skip) {
        return wrap(builder.append(SkipAction.create(builder, skip)));
    }
    
    /**************************************************************************/
    /*                          Terminating Actions                           */
    /**************************************************************************/
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer<? super T> consumer) {
        ForEachTerminator.create(builder, (Consumer<T>) consumer).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEachOrdered(Consumer<? super T> consumer) {
        sequential().forEach(consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return ToArrayTerminator.create(builder, Object[]::new).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <A> A[] toArray(IntFunction<A[]> instantiator) {
        return ToArrayTerminator.create(builder, instantiator).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T reduce(T identity, BinaryOperator<T> combiner) {
        return ReduceTerminator.create(builder, identity, combiner).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> reduce(BinaryOperator<T> combiner) {
        return Optional.ofNullable(ReduceTerminator.create(builder, combiner).execute());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return ReduceTerminator.create(builder, identity, (BiFunction<U, T, U>) accumulator, combiner).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return CollectTerminator.create(builder, supplier, accumulator, combiner).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return CollectTerminator.create(builder, (Collector<T, A, R>) collector).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> min(Comparator<? super T> cmprtr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> max(Comparator<? super T> cmprtr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return CountTerminator.create(builder).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean anyMatch(Predicate<? super T> prdct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean allMatch(Predicate<? super T> prdct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean noneMatch(Predicate<? super T> prdct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> findFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> findAny() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**************************************************************************/
    /*                   Inherited Methods from Base Stream                   */
    /**************************************************************************/
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParallel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> sequential() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> parallel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> unordered() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> onClose(Runnable r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**************************************************************************/
    /*                             Constructor                                */
    /**************************************************************************/
    
    private MutableStream(HasNext<T, Stream<T>> builder) {
        this.builder = requireNonNull(builder);
    }
    
    private final HasNext<T, Stream<T>> builder;
}