# Kotlin Project

Kotlin implementations of coding interview algorithms. For comprehensive documentation, see `../docs/kotlin/docs/README.md`.

## Project Structure

```
kotlin/
├── src/main/kotlin/algorithms/  - All algorithm implementations
├── build.gradle.kts             - Gradle build configuration
└── README.md                    - This file
```

**Algorithm categories:** arrays, strings, dynamic_programming, prefix_sum, sliding_window, sorting, stack_queue, greedy, math, bit_manipulation, recursion, patterns, basics


## Running Code

### Using Gradle
```bash
./gradlew run
```

### Using Kotlin Compiler
```bash
kotlinc FileName.kt -include-runtime -d FileName.jar && java -jar FileName.jar
```

### Using IDE
- IntelliJ IDEA / Android Studio: Right-click file → Run
- Or click the green play button next to the main function

## Documentation

For comprehensive guides, algorithm explanations, and interview prep strategies, see:
- **[Complete Documentation](../docs/kotlin/docs/README.md)** - All guides and references
- **[Interview Patterns](../docs/kotlin/docs/interview-prep/interview_patterns.md)** - Problem-solving patterns
- **[Kotlin Basics](../docs/kotlin/docs/kotlin-basics/)** - Language fundamentals
