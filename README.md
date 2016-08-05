# Mutable Stream
A special implementation of the Java 8 Streams that can be modified until they are terminated. This makes it possible to create dynamic stream implementations that can optimize the stream operations as they are created, for an example by reflecting the stream actions as parameters in a database query.

Streams can be created by wrapping a source-action in the MutableStream-class. A source action is similar to an iterator in that it has a `hasNext`- and a `next`-method, but it can also implement the `append`-method to make it possible to consume actions as they are appended to the stream.

# License
This project is licensed under the Apache 2 license with the intention that it can be included in the Speedment project once it is stable enough.
