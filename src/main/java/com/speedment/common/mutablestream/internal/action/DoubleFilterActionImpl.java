package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.DoubleFilterAction;
import java.util.function.DoublePredicate;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class DoubleFilterActionImpl extends AbstractAction<Double, DoubleStream, Double, DoubleStream> implements DoubleFilterAction {

    private final DoublePredicate predicate;
    
    public DoubleFilterActionImpl(HasNext<Double, DoubleStream> previous, DoublePredicate predicate) {
        super(previous);
        this.predicate = requireNonNull(predicate);
    }
    
    @Override
    public DoublePredicate getPredicate() {
        return predicate;
    }
    
    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Double, DoubleStream, Q, QS> next) {
        return next;
    }
    
    @Override
    public DoubleStream build(boolean parallel) {
        return previous().build(parallel).filter(predicate);
    }
}