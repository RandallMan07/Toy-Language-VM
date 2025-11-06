# Toy VM — Notes on Skeleton Modifications

This README documents the **small but necessary** changes I made to the provided skeleton to complete the assignment and tooling.

## TL;DR
- Exposed a few fields in core classes to avoid reflection/boilerplate.
- Added hooks to inject built‑ins early.
- Extended the CLI and shell wrapper for better debugging.
- Introduced two thin AST wrapper nodes and a `compile()` pass on AST nodes.
- Distinguished **parameters** from **locals** in frame layout to enable `getArity()`.
- Tweaked a micro-benchmark to avoid builtin name clashes (my VM forbids redefining builtins).

---

## Modified Components (by area)

### 1) Public API adjustments
- **`RootCallTarget`**: exposed minimal getters/fields so the runtime and tools can query the call target safely (e.g., arity/name).
- **`ToyRootNode`**: exposed selected attributes needed by the launcher and the BCI toolchain (e.g., function name / entry).

> Rationale: the skeleton kept these private; exposing them avoids invasive plumbing and simplifies integration with the launcher and debugger.

### 2) Builtins injection
- **`ToyLauncher`**: added a small initialization hook to **inject builtins** before user code loads.  
  This makes builtin availability deterministic across the interpreter and the BCI loop.

### 3) CLI + shell script quality-of-life
- **`toy` shell script**: added flags
  - `-dump-bci` — dump bytecode after compilation for each function
  - `-trace-bci` — trace execution of the BCI loop (program counter, stack snapshots)
  
  These are pass-through switches that reach the launcher/runtime and toggle debugging output.

### 4) AST & frontend
- All **`ToyAstNode`**s now implement/forward a `compile(CompileContext ctx)` method.
- Extended **`ToyNodeFactory`** to construct nodes with compile support and to route new wrappers.
- Added two minimal **wrapper nodes**:
  - `ToyFunctionBodyNode` — wraps a function body to centralize prologue/epilogue concerns.
  - `ToyStatementExpressionNode` — normalizes “stmt vs expr” sites (useful for uniform compilation and tracing).
  
> These wrappers do not change language semantics; they only streamline compilation and tracing.

### 5) Frame layout & arity
- Frame layout now **separates parameters from locals** in the `FrameDescriptor`.  
  This enables a reliable `getArity()` on the call target and simplifies argument handling in the interpreter/BCI.

### 6) Benchmark rename
- One micro‑benchmark referenced a function with the **same name as a builtin**.  
  My implementation **does not allow redefining builtins**, so I **renamed** the user function in that benchmark to avoid the clash.  
  (No behavior or complexity changes to the benchmark itself.)

---

## Why these changes?
- Keep the core semantics intact, but make the toolchain (launcher/BCI/debug) **observable** and **traceable**.
- Allow the compiler and runtime to share the minimum metadata needed (name/arity).
- Keep AST construction simple while enabling a **single-pass `compile()`** lowering into bytecode.

---