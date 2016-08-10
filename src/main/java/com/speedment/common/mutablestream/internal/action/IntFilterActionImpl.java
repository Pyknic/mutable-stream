package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.IntFilterAction;
import java.util.function.IntPredicate;
import java.util.stream.BaseStream;
import java.util.stream.IntStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class IntFilterActionImpl extends AbstractAction<Integer, IntStream, Integer, IntStream> implements IntFilterAction {

    private final IntPredicate predicate;
    
    public IntFilterActionImpl(HasNext<Integer, IntStream> previous, IntPredicate predicate) {
        super(previous);
        this.predicate = requireNonNull(predicate);
    }
    
    @Override
    public IntPredicate getPredicate() {
        return predicate;
    }
    
    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Integer, IntStream, Q, QS> next) {
        return next;
    }
    
    @Override
    public IntStream build(boolean parallel) {
        return previous().build(parallel).filter(predicate);
    }
}