Here’s a simple `README.md` tailored for your Java 24 Gatherer exercises project:

````markdown
# Java 24 Gatherer Exercises

This project provides practice implementations and tests for using Java 24's new `Stream::gather` and `Gatherers` API.

## 🧩 Features

- Java 24 streams with gatherers
- Unit-tested using JUnit 5 and AssertJ
- Maven-based project structure
- Example gatherers:
  - `gatherAdjacentDuplicates`
  - `slidingWindow`
  - `bufferUntil`

## 📦 Requirements

- Java 24+
- Maven 3.9+

## 🚀 How to Run

1. **Clone the project** or run the setup script
2. **Run tests**:

```bash
mvn clean test
````

3. **Edit code** in:

* `src/main/java/dev/himnabil/gatherers/{GathererName}.java`
* `src/test/java/dev/himnabil/gatherers/{GathererName}Test.java`

## 🧪 Example

```java
var result = Stream.of("a", "a", "b", "b")
    .gather(AdjacentDuplicatesGatherer.gatherer())
    .toList();

// result => [["a", "a"], ["b", "b"]]
```

## 📁 Project Layout

```
java24-gatherer-exercises/
├── pom.xml
├── README.md
└── src/
    ├── main/java/dev/himnabil/gatherers/{GathererName}.java
    └── test/java/dev/himnabil/gatherers/{GathererName}Test.java
```

## 🛠️ Author

Created by [Nabil H.](https://github.com/himnabil)
