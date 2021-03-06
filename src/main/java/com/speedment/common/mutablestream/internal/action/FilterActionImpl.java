package com.speedment.common.mutablestream.internal.action;

import java.util.function.Predicate;
import java.util.stream.Stream;
import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.FilterAction;
import com.speedment.common.mutablestream.action.Action;
import java.util.stream.BaseStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @param <T>  the filtered type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class FilterActionImpl<T> 
extends AbstractAction<T, Stream<T>, T, Stream<T>> 
implements FilterAction<T> {

    private final Predicate<T> predicate;
    
    public FilterActionImpl(HasNext<T, Stream<T>> previous, Predicate<T> predicate) {
        super(previous);
        this.predicate = requireNonNull(predicate);
    }
    
    @Override
    public Predicate<T> getPredicate() {
        return predicate;
    }
    
    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<T, Stream<T>, Q, QS> next) {
        return next;
    }

    @Override
    public Stream<T> build(boolean parallel) {
        return previous().build(parallel).filter(predicate);
    }
}