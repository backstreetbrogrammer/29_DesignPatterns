# Design Patterns in Java

> This is a Java course to understand design patterns

Tools used:

- JDK 11
- Maven
- JUnit 5, Mockito
- IntelliJ IDE

## Table of contents

1. [Introduction](https://github.com/backstreetbrogrammer/29_DesignPatterns#chapter-01-introduction)
2. [Creational Patterns](https://github.com/backstreetbrogrammer/29_DesignPatterns#chapter-02-creational-patterns)
    - [Singleton](https://github.com/backstreetbrogrammer/29_DesignPatterns#singleton)
    - [Builder](https://github.com/backstreetbrogrammer/29_DesignPatterns#builder)
    - [Factory](https://github.com/backstreetbrogrammer/29_DesignPatterns#factory)
3. Structural Patterns
    - Decorator
    - Adaptor
4. Behavioral Patterns
    - Strategy
    - Observer
    - Template
    - Chain of responsibility

---

### Chapter 01. Introduction

Design patterns are typical solutions to common problems in software design. Each pattern is like a blueprint that we
can customize to solve a particular design problem in our code.

Design patterns differ by their complexity, level of detail and scale of applicability. In addition, they can be
categorized by their intent and divided into three groups:

- **Creational Patterns** provide object creation mechanisms that increase flexibility and reuse of existing code.
- **Structural Patterns** explain how to assemble objects and classes into larger structures, while keeping these
  structures flexible and efficient.
- **Behavioral Patterns** take care of effective communication and the assignment of responsibilities between objects.

---

### Chapter 02. Creational Patterns

**Creational Patterns** deal with the process of creation of objects of classes.

#### Singleton

- Ensures a class has only one instance
- Provides a global access point to that instance
- Java's implementation makes use of a private constructor, a static method combined with a static variable.

```java
public class Singleton {

    // write once when the class is initialized - always thread safe
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance; // no lock required for reads by multiple threads
    }

    // other useful methods

}
```

There is one caveat: a privileged client can invoke the private constructor reflectively with the aid of the
`AccessibleObject.setAccessible()` method. If we need to defend against this attack, we can modify the constructor to
make it throw an exception if it’s asked to create a second instance.

The MOST correct, clean and concise implementation of Singleton design pattern is to use **ENUM**.

```java
public enum Singleton {
    INSTANCE;

    // other useful methods
}
```

The same way it is used all over JDK APIs, for ex:

```java
enum NaturalOrderComparator implements Comparator<Comparable<Object>> {
    INSTANCE;

    @Override
    public int compare(final Comparable<Object> c1, final Comparable<Object> c2) {
        return c1.compareTo(c2);
    }

    @Override
    public Comparator<Comparable<Object>> reversed() {
        return Comparator.reverseOrder();
    }
}
```

#### Builder

Used to encapsulate the construction of a product and allow it to be constructed in steps.

Instead of making the desired object directly, the client calls a constructor (or static factory) with all the
**required** parameters and gets a **builder** object.

Then the client calls setter-like methods on the builder object to set each optional parameter of interest.

Finally, the client calls a parameterless build method to generate the object, which is typically **immutable**. The
builder is typically a `static` member class of the class it builds.

```java
// Builder Pattern
public class Pizza {

    private int size; // required
    private boolean cheese;
    private boolean pepperoni;
    private boolean bacon;

    public static class Builder {
        //required
        private final int size;

        //optional
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean bacon = false;

        public Builder(final int size) {
            this.size = size;
        }

        public Builder cheese(final boolean value) {
            cheese = value;
            return this;
        }

        public Builder pepperoni(final boolean value) {
            pepperoni = value;
            return this;
        }

        public Builder bacon(final boolean value) {
            bacon = value;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    private Pizza(final Builder builder) {
        size = builder.size;
        cheese = builder.cheese;
        pepperoni = builder.pepperoni;
        bacon = builder.bacon;
    }

}
```

Code snippet to use builder pattern:

```
Pizza pizza = new Pizza.Builder(12)
                       .cheese(true)
                       .pepperoni(true)
                       .bacon(true)
                       .build();
```

This client code is easy to write and, more importantly, easy to read.

The `Pizza` class is **immutable**, and all parameter default values are in one place. The builder’s setter methods
return the builder itself so that invocations can be chained, resulting in a _fluent_ API.

#### Factory

