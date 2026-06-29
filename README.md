# Java Swing Utilities (EDN Design)

> Desktop GUI applications built with Java Swing — a scientific calculator and a mouse auto-clicker utility.

**Java Swing 桌面工具集** — 包含基礎計算機與滑鼠自動點擊器，展示標準 Java GUI 設計模式。

---

## Overview

This repository contains two standalone Swing desktop applications organized under a clean Maven project structure. Each application is separated into its own package with dedicated business logic and UI layers.

### Applications

| Application | Package | Description |
|-------------|---------|-------------|
| **Simple Calculator** | `com.edndesign.calculator` | Arithmetic calculator with operator precedence |
| **Mouse Auto Clicker** | `com.edndesign.mouse` | Configurable automated mouse click utility |

---

## Key Features

### Simple Calculator
- Clean grid-layout button panel with color-coded controls
- Expression evaluation with `* /` precedence over `+ -`
- Input validation and division-by-zero protection
- Extracted `ExpressionEvaluator` for testable business logic

### Mouse Auto Clicker
- Configurable click count and delay (milliseconds)
- Uses `java.awt.Robot` for OS-level mouse simulation
- Input validation with user-friendly error dialogs
- Instance-based design (no static UI state)

---

## Tech Stack

![Java](https://img.shields.io/badge/Java-11+-ED8B00?logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-Desktop%20GUI-6DB33F)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)

---

## Project Structure

```
java-s-edndesign/
├── pom.xml
└── src/main/java/com/edndesign/
    ├── calculator/
    │   ├── SimpleCalculatorApp.java    # Calculator UI
    │   └── ExpressionEvaluator.java      # Expression parsing logic
    └── mouse/
        └── MouseAutoClickerApp.java      # Auto-clicker UI + Robot control
```

---

## Getting Started

### Prerequisites

- JDK 11 or later
- Apache Maven 3.6+

### Build

```bash
git clone https://github.com/foolguyisme/java-s-edndesign.git
cd java-s-edndesign
mvn compile
```

### Run Calculator

```bash
mvn exec:java -Dexec.mainClass="com.edndesign.calculator.SimpleCalculatorApp"
```

Or without Maven:

```bash
javac -d target/classes src/main/java/com/edndesign/calculator/*.java
java -cp target/classes com.edndesign.calculator.SimpleCalculatorApp
```

### Run Mouse Auto Clicker

```bash
mvn exec:java -Dexec.mainClass="com.edndesign.mouse.MouseAutoClickerApp"
```

---

## License

MIT License — see repository for details.
