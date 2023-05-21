package com.backstreetbrogrammer.behavioral;

public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;

    public void setSuccessor(final ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(final T input) {
        final T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}
