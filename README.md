# 🧠 Toy Virtual Machine

A fully functional **virtual machine and interpreter** for a dynamically typed language, implemented in **Java**.  

---

## 🚀 Overview

This project implements a **stack-based virtual machine** capable of parsing, interpreting, and optimizing execution of a toy high-level language (Simple/ToyLang).  
It follows key design principles from **Truffle/GraalVM** and modern **language runtime architectures**.

### ✨ Core Features
- **Bytecode-based execution engine**  
  Custom compiler emits compact bytecode instructions interpreted by the VM loop.

- **Hidden Classes / Shapes**
  Efficient object representation inspired by JavaScript engines like V8 and Truffle.

- **Inline Caching (IC)**
  Monomorphic and polymorphic inline caches to optimize dynamic property access.

- **Array Strategies**
  Adaptive array storage depending on content types (int[], double[], Object[]).

- **Lazy Strings / Ropes**
  Memory-efficient string concatenation and slicing without immediate flattening.

- **(Optional) JIT Compilation**
  Minimal experimental JIT tier to compile hot functions or loops.

---

## 🏗️ Architecture

```
Source Code (.sl)
     ↓
 Parser & Compiler
     ↓
 Bytecode (Toy BCI)
     ↓
 Toy Virtual Machine
     ↓
 [Optimizations: ICs, Shapes, Strategies]
     ↓
 Execution / Profiling
```

Key components:
- `ToyBciLoop` – the main interpreter loop  
- `VObject`, `Shape` – object model and property storage  
- `ToyCompiler` – frontend bytecode generator  
- `ToyBuiltins` – built-in library functions

---

## 🧪 Example

```js
function main() {
    o = new();
    o.x = 42;
    println(o.x);
}
```

**Execution:**
```bash
$ toy examples/object.sl
42
```

---

## 📈 Benchmarks

Includes micro-benchmarks comparing **interpreted** vs **inline-cached** execution:
```
Monomorphic IC benchmark: 10 runs, 1,000,000 iterations/run
Without IC: 2300 ms
With IC:    1850 ms
Speedup:    ~1.25×
```

---

## 🔍 Technologies & Skills Demonstrated

- Java 15+
- Virtual machine internals
- Bytecode design and interpretation
- Dynamic object models (hidden classes)
- Inline caching and specialization
- Memory-efficient data structures
- Performance benchmarking and tracing

---

## 📚 References

- [Truffle & GraalVM Papers](https://www.graalvm.org/research/)
- V8 “Shapes” and Inline Cache design
- TU/e Virtual Machines course materials

---

## 👤 Author

**Adrià Martín**  
📧 [adria.martinma@autonoma.cat]  
🔗 [github.com/adriamartin](https://github.com/RandallMan07)

**Pau Jaso**
📧 [pau.jaso@autonoma.cat]  
🔗 [github.com/pau-jaso](https://github.com/Jaso12)

---

> *“A VM is not just an interpreter — it’s a dynamic runtime capable of learning how your program behaves.”*
