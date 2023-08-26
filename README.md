# isin-generator
Attempt at solving a failed interview test in a Modern Java fashion

# Java 9 Modules

Using Java 9 modules we are now able to control the classes that are exposed to the outside at a module level. 
The goal here is to expose the following classes in this fashion: 

## Consumption

When consuming we consume from a target module regardless of its inner package structure.

[isin.generator.application] -(consumes)-> [isin.generator.service]
[isin.generator.service] -(consumes) -> [isin.generator.model]
[isin.generator.model]

## Exposing

When exposing what we expose are either single classes or classes contained in the exposed package.

[isin.generator.application]
[isin.generator.service] -(exposes) -> [com.achevrier.model.generator]
[isin.generator.model] -(exposes)-> [com.achevrier.model]

# Singleton pattern

Since we mostly use threaf-safe constructs we can use enums as a way to declare singletons. 

```java
public enum MySingleton {
    INSTANCE; // This is a way of declaring a single instance of MySingleton 
    
    public void myFunction() {
        // any code can go here
    }
}
```

# Controlling the number of thread in a parallel Stream

There are two ways to control the number of thread running in a parallel Stream in Java:
- Changing the number of threads in the default ForkJoinPool via this property (java.util.concurrent.ForkJoinPool.common.parallelism)
- Using a ForkJoinPool and setting the number of threads at runtime

