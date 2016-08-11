package com.speedment.common.mutablestream.internal.action;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.action.Action;
import com.speedment.common.mutablestream.action.LongFilterAction;
import java.util.function.LongPredicate;
import java.util.stream.BaseStream;
import java.util.stream.LongStream;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class LongFilterActionImpl extends AbstractAction<Long, LongStream, Long, LongStream> implements LongFilterAction {

    private final LongPredicate predicate;
    
    public LongFilterActionImpl(HasNext<Long, LongStream> previous, LongPredicate predicate) {
        super(previous);
        this.predicate = requireNonNull(predicate);
    }
    
    @Override
    public LongPredicate getPredicate() {
        return predicate;
    }
    
    @Override
    public <Q, QS extends BaseStream<Q, QS>> HasNext<Q, QS> append(Action<Long, LongStream, Q, QS> next) {
        return next;
    }
    
    @Override
    public LongStream build(boolean parallel) {
        return previous().build(parallel).filter(predicate);
    }
}