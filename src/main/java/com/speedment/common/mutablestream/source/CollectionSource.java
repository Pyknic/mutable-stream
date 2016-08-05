package com.speedment.common.mutablestream.source;

import com.speedment.common.mutablestream.HasNext;
import com.speedment.common.mutablestream.internal.source.CollectionSourceImpl;
import java.util.Collection;
import java.util.stream.Stream;

/**
 *
 * @param <T>  the streamed type
 * 
 * @author  Emil Forslund
 * @since   1.0.0
 */
public interface CollectionSource<T> extends HasNext<T, Stream<T>> {
    
    static <T> CollectionSource<T> create(Collection<T> collection) {
        return new CollectionSourceImpl<>(collection);
    }
    
}