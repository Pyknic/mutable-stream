package com.speedment.common.mutablestream.internal.terminate;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.terminate.IteratorTerminator;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public final class IteratorTerminatorImpl<T> 
extends AbstractTerminator<T, Stream<T>, Iterator<T>> 
implements IteratorTerminator<T> {

    public IteratorTerminatorImpl(HasNext<T, Stream<T>> previous, boolean parallel) {
        super(previous, parallel);
    }

    @Override
    public Iterator<T> execute() {
        try (final Stream<T> stream = buildPrevious()) {
            return stream.iterator();
        }
    }
}