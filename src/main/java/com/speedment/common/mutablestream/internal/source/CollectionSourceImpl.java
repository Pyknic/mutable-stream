package com.speedment.common.mutablestream.internal.source;

import com.speedment.common.mutablestream.source.CollectionSource;
import java.util.Collection;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.terminate.Terminator;
import java.util.stream.BaseStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class CollectionSourceImpl<T> implements CollectionSource<T> {
    
    private final Collection<T> collection;
    
    public CollectionSourceImpl(Collection<T> collection) {
        this.collection = requireNonNull(collection);
    }

    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<T, Stream<T>, Q, QS> next) {
        return next;
    }

    @Override
    public <X> X execute(Terminator<T, Stream<T>, X> terminator) {
        return terminator.execute();
    }

    @Override
    public Stream<T> build(boolean parallel) {
        return parallel ? collection.parallelStream() : collection.stream();
    }
}