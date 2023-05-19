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
3. [Structural Patterns](https://github.com/backstreetbrogrammer/29_DesignPatterns#chapter-03-structural-patterns)
    - [Decorator](https://github.com/backstreetbrogrammer/29_DesignPatterns#decorator)
    - [Adaptor](https://github.com/backstreetbrogrammer/29_DesignPatterns#adaptor)
4. [Behavioral Patterns](https://github.com/backstreetbrogrammer/29_DesignPatterns#chapter-04-behavioral-patterns)
    - [Strategy](https://github.com/backstreetbrogrammer/29_DesignPatterns#strategy)
    - [Observer](https://github.com/backstreetbrogrammer/29_DesignPatterns#observer)
    - [Template](https://github.com/backstreetbrogrammer/29_DesignPatterns#template)
    - [Chain of responsibility](https://github.com/backstreetbrogrammer/29_DesignPatterns#chain-of-responsibility)

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

Defines an interface for creating an object, but lets subclasses decide which class to instantiate. Factory Method lets
a class defer instantiation to subclasses.

In other words, The factory design pattern lets us create objects **without exposing the instantiation logic** to the
client.

Suppose that we’re working for a bank that needs a way of creating different financial products: loans, bonds, stocks,
and so on. Typically, we’d create a Factory class with a method that’s responsible for the creation of different
objects, as shown here:

```java
public class ProductFactory {

    public static Product createProduct(final String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }
}
```

Here, `Loan`, `Stock`, and `Bond` are subtypes of `Product`. The `createProduct()` method could have additional logic to
configure each created product. But the benefit is that we can create these objects without exposing the constructor and
the configuration to the client, which makes the creation of products simpler for the client, as follows:

```
Product p = ProductFactory.createProduct("loan");
```

---

### Chapter 03. Structural Patterns

The Structural Patterns explain how to assemble objects and classes into larger structures, while keeping these
structures flexible and efficient, e.g. Adapter and Decorator.

#### Decorator

A Decorator pattern can be used to attach additional responsibilities to an object either statically or dynamically.
Decorators provide a flexible alternative to sub-classing for extending functionality.

In the implementation of this pattern, we prefer **composition** over an **inheritance** – so that we can reduce the
overhead of subclassing again and again for each decorating element.

Suppose we have a `Pizza` object, and we want to decorate it. The decoration does not change the object itself; it's
just that in addition to the `Pizza`, we're adding some decoration items like cheese, pepperoni, bacon, etc.

```java
public interface Pizza {
    String decorate();
}
```

The implementation of this interface will look like:

```java
public class PizzaImpl implements Pizza {
    @Override
    public String decorate() {
        return "Yummy Pizza";
    }
}
```

We'll now create an **abstract** `PizzaDecorator` class for this pizza. This decorator will implement the `Pizza`
interface as well as hold the same object. The implemented method from the same interface will simply call
the `decorate()` method from our interface:

```java
public abstract class PizzaDecorator implements Pizza {
    private Pizza pizza;

    public PizzaDecorator(final Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String decorate() {
        return pizza.decorate();
    }
}
```

We'll now create some decorating element. These decorators will extend our abstract `PizzaDecorator` class and will
modify its `decorate()` method according to our requirement:

```java
public class Pepperoni extends PizzaDecorator {

    public Pepperoni(final Pizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return String.format("%s%s", super.decorate(), decorateWithPepperoni());
    }

    private String decorateWithPepperoni() {
        return " with fresh pepperoni";
    }

}
```

Similarly, we can create decorators for cheese and bacon.

```java
public class Cheese extends PizzaDecorator {

    public Cheese(final Pizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return String.format("%s%s", super.decorate(), decorateWithCheese());
    }

    private String decorateWithCheese() {
        return " with home made cheese";
    }

}
```

```java
public class Bacon extends PizzaDecorator {

    public Bacon(final Pizza pizza) {
        super(pizza);
    }

    public String decorate() {
        return String.format("%s%s", super.decorate(), decorateWithBacon());
    }

    private String decorateWithBacon() {
        return " with super tasty bacon";
    }

}
```

Here is the main code to show decorator pattern.

```
        final Pizza pizza1 = new Pepperoni(new PizzaImpl());
        assertEquals(pizza1.decorate(), "Yummy Pizza with fresh pepperoni");

        final Pizza pizza2 = new Bacon(new Cheese(new Pepperoni(new PizzaImpl())));
        assertEquals(pizza2.decorate(), "Yummy Pizza with fresh pepperoni with home made cheese with super tasty bacon");
```

Note that in the first `pizza1` object, we're only decorating it with `Pepperoni`, while for the other `pizza2` object
we're decorating with `Bacon`, `Cheese` and `Pepperoni`. This pattern gives us this flexibility to add as many
decorators as we want at runtime.

#### Adaptor

---

### Chapter 04. Behavioral Patterns

#### Strategy

#### Observer

#### Template

#### Chain of responsibility

---
