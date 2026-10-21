# Installation

## Table of Contents

- [Prerequisites](#prerequisites)
- [Download](#download)
- [Build from Source](#build-from-source)
- [Installation](#installation-1)
- [Usage](#usage)
- [Troubleshooting](#troubleshooting)

---

## Prerequisites

- [Minecraft](https://minecraft.net/) 26.3
- [Fabric Loader](https://fabricmc.net/use/) 0.20.1+ **or** [Quilt Loader](https://quiltmc.org/en/install/) 0.26.0+
- [Fabric API](https://modrinth.com/mod/fabric-api) 0.164.0+ (Fabric) **or** Quilted Fabric API (Quilt)
- [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin) 1.14.2+
- Java 25

Quilt support is currently pending a `Quilted Fabric API` release for Minecraft 26.3; the JAR already includes the necessary `quilt.mod.json` metadata.

## Download

Download the latest `.jar` from the [Releases](https://github.com/XodiumSoftware/randomblockplacement/releases) page.

## Build from Source

Build the mod using Gradle.

### Prerequisites

- [JDK 25](https://adoptium.net/)
- [Git](https://git-scm.com/)

### Setup

1. Clone the repository:

    ```bash
    git clone git@github.com:XodiumSoftware/randomblockplacement.git
    cd randomblockplacement
    ```

2. Build the mod:

    ```bash
    just build
    ```

    Or with Gradle directly:

    ```bash
    ./gradlew build
    ```

3. The output JAR is at:

    ```
    build/libs/randomblockplacement-*.jar
    ```

### Run a Test Client

To quickly test the mod:

```bash
just run
```

This starts a local Minecraft client with the mod loaded.

## Installation

1. Install **Fabric Loader** or **Quilt Loader** for Minecraft 26.3.
2. Download `randomblockplacement-<version>.jar`, the matching API mod (`Fabric API` or `Quilted Fabric API`), and `Fabric Language Kotlin`.
3. Place all `.jar` files into your Minecraft `mods/` folder.
4. Launch Minecraft with the Fabric or Quilt profile.

## Usage

- **Toggle Random Placement**: Press the bound key (default: `R`) to toggle random block placement mode.
- **Block Placement**: When the mod is active, every time you place a block, a random block from your hotbar is selected.
- **HUD Indicator**: A small icon appears on the HUD while the mode is active.

## Troubleshooting

### Build fails

- Verify Java 25 is installed and active:

    ```bash
    java -version
    ```

- Make sure `JAVA_HOME` is set correctly.
- Try cleaning the build:

    ```bash
    ./gradlew clean
    ./gradlew build
    ```

### Ktlint errors

The project uses ktlint for code style. Fix formatting:

```bash
just format
```

### Mod crashes on launch

- Ensure the correct API mod (`Fabric API` / `Quilted Fabric API`) and `Fabric Language Kotlin` are installed.
- Check that the Minecraft version matches the mod's target version.
- Quilt support for Minecraft 26.3 requires a matching `Quilted Fabric API` release.

---

<p align="right"><a href="#readme-top">▲</a></p>
