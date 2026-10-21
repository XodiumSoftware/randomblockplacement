# RandomBlockPlacement — Agents Context

## Project at a Glance

- **Name:** RandomBlockPlacement
- **Type:** Minecraft Fabric/Quilt client mod
- **MC Version:** 26.3
- **Language:** Kotlin (JVM 25)

## APIs & Tools

| Category           | Technology                                       | Purpose                           |
| ------------------ | ------------------------------------------------ | --------------------------------- |
| **Core API**       | [Fabric API](https://fabricmc.net/) 0.164.0+26.3 | Minecraft mod API (Fabric)        |
| **Quilt API**      | [Quilted Fabric API](https://quiltmc.org/)       | Fabric API compatibility on Quilt |
| **Language**       | Kotlin 2.4.21                                    | JVM language                      |
| **Build Tool**     | Gradle (Kotlin DSL)                              | Build automation                  |
| **Gradle Plugins** | `net.fabricmc.fabric-loom` 1.18-SNAPSHOT         | Fabric mod development            |
|                    | kotlin("jvm") 2.4.21                             | Kotlin compilation                |
|                    | ktlint 12.3.0                                    | Kotlin linting                    |
| **Runtime**        | fabric-language-kotlin 1.14.2+kotlin.2.4.21      | Kotlin runtime on Fabric clients  |
| **Code Style**     | ktlint                                           | Kotlin linting                    |

## Quick Commands

Uses [just](https://github.com/casey/just) as the command runner. Run `just` to list all recipes.

```bash
# Build the mod JAR (outputs in build/libs/)
just build

# Run the Minecraft client with the mod
just run

# Run linting
just lint

# Fix linting issues
just format

# Build and run the client
just dev
```

## Project Structure

```
randomblockplacement/       # Repo root
├── build.gradle.kts        # Module build configuration
├── settings.gradle.kts     # Project name + plugin repositories
├── gradle.properties       # Versions and Gradle options
├── justfile                # Command runner recipes
├── .editorconfig           # Code style rules
├── AGENTS.md               # This file
├── GUIDE.md                # Player/build guide
├── README.md               # Project overview
├── src/main/kotlin/        # Common (main) Kotlin source
│   └── org/xodium/
│       ├── RandomBlockPlacement.kt
│       └── data/
│           └── RandomBlockPlacementDataGenerator.kt
├── src/client/kotlin/      # Client-side Kotlin source
│   └── org/xodium/
│       ├── RandomBlockPlacementClient.kt
│       ├── RandomBlockPlacementRenderer.kt
│       ├── KeyBindings.kt
│       └── mixin/client/
│           └── PlayerInteractBlockMixin.kt
├── src/main/resources/     # Common resources
│   ├── fabric.mod.json
│   ├── randomblockplacement.mixins.json
│   └── assets/randomblockplacement/
└── src/client/resources/   # Client-only resources
    └── randomblockplacement.client.mixins.json
```

## Architecture

### Entry Points

1. **RandomBlockPlacement** (`ModInitializer`) — Common entry point. Logs initialization.
2. **RandomBlockPlacementClient** (`ClientModInitializer`) — Client entry point. Registers the key binding, ticks the random-placement logic, and renders the HUD indicator.
3. **RandomBlockPlacementDataGenerator** (`DataGeneratorEntrypoint`) — Data generator entry point (currently empty; Fabric only).

The JAR also includes a `quilt.mod.json` so Quilt Loader can load the mod.

### Module System

- All entry points are Kotlin `object` singletons.
- The mod uses compile-time constants only — there is no file-based configuration.
- Client mixins live in `org.xodium.mixin.client` and are declared in `randomblockplacement.client.mixins.json`.

### Key Conventions

- Most internal classes use `internal` visibility.
- The common entry point (`RandomBlockPlacement`) is `public` so its `MOD_ID` helper is accessible from the client source set across Loom's split environment source sets.
- Entry points are `object` singletons loaded through the `kotlin` adapter from `fabric-language-kotlin`.
- Minecraft 26.3 is unobfuscated, so no `mappings` dependency or Intermediary is used; code references official Mojang-style class names (e.g., `Minecraft`, `LocalPlayer`, `Identifier`).
- ktlint is enforced; wildcard imports are disabled globally via `.editorconfig`.
- **Import types instead of using fully qualified paths**.
- **Use `it` for single-parameter lambdas**.
- **Don't create intermediate `const val` for override properties** — assign directly.
- **Don't add KDoc to implemented overrides** — the base interface already documents them.

### Code Structure (in interfaces, classes, objects)

Order members from top to bottom:

1. **`const val`** — compile-time constants
2. **`val`** — read-only properties (overrides first)
3. **`var`** — mutable properties (overrides first)
4. **`fun`** — functions (overrides first)

Within each group:

- **`override`** members go above regular members
- **`public`** members go above **`private`** members

## Testing

- No automated tests in this project.
- Test by running `just run` and manually verifying in-game.

## Important Notes

- `fabric-language-kotlin` is a **required** dependency for players because the mod is written in Kotlin.
- Resources remain under `src/main/resources` and `src/client/resources` because Loom's split environment source sets require environment-specific resource directories.
