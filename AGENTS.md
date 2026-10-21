# RandomBlockPlacement — Agents Context

## Project at a Glance

- **Name:** RandomBlockPlacement
- **Type:** Minecraft Fabric client mod
- **MC Version:** 26.3
- **Language:** Kotlin (JVM 25)

## APIs & Tools

| Category           | Technology                                       | Purpose                          |
| ------------------ | ------------------------------------------------ | -------------------------------- |
| **Core API**       | [Fabric API](https://fabricmc.net/) 0.102.0+1.21 | Minecraft mod API                |
| **Language**       | Kotlin 2.4.21                                    | JVM language                     |
| **Build Tool**     | Gradle (Kotlin DSL)                              | Build automation                 |
| **Gradle Plugins** | fabric-loom 1.10-SNAPSHOT                        | Fabric mod development           |
|                    | kotlin("jvm") 2.1.0                              | Kotlin compilation               |
|                    | ktlint 12.3.0                                    | Kotlin linting                   |
| **Runtime**        | fabric-language-kotlin 1.13.0+kotlin.2.1.0       | Kotlin runtime on Fabric clients |
| **Code Style**     | ktlint                                           | Kotlin linting                   |

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
├── src/                    # Common (main) Kotlin source
│   ├── RandomBlockPlacement.kt
│   └── data/
│       └── RandomBlockPlacementDataGenerator.kt
├── src/client/             # Client-side Kotlin source
│   ├── RandomBlockPlacementClient.kt
│   ├── RandomBlockPlacementRenderer.kt
│   ├── KeyBindings.kt
│   └── mixins/client/
│       └── PlayerInteractBlockMixin.kt
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
3. **RandomBlockPlacementDataGenerator** (`DataGeneratorEntrypoint`) — Data generator entry point (currently empty).

### Module System

- All entry points are Kotlin `object` singletons.
- The mod uses compile-time constants only — there is no file-based configuration.
- Client mixins live in `org.xodium.mixin.client` and are declared in `randomblockplacement.client.mixins.json`.

### Key Conventions

- All internal classes use `internal` visibility.
- Entry points are `object` singletons loaded through the `kotlin` adapter from `fabric-language-kotlin`.
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
