# Random Block Placement Mod

## Overview

The **Random Block Placement Mod** is a Minecraft Fabric client mod that adds a randomized block placement feature. When activated, the mod randomly selects which block to place from your hotbar, adding an element of unpredictability to building.

## Features

- **Random block selection** from your hotbar when placing blocks.
- **Toggleable mode** using a customizable keybind (default: `R`).
- **HUD Indicator** to show when randomization is active.
- **Supports all block items** from Vanilla and mods.

## How to Use

1. Press **`R`** to toggle **Random Block Placement Mode**.
2. When enabled, every time you place a block, a random block from your hotbar is selected.
3. A small icon appears on the HUD when the mode is active.

## Installation

1. **Download** the latest release from the [Releases](https://github.com/XodiumSoftware/randomblockplacement/releases) page.
2. Install the required dependencies:
    - [Fabric Loader](https://fabricmc.net/use/)
    - [Fabric API](https://modrinth.com/mod/fabric-api)
    - [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin)
3. Place the downloaded `.jar` file into your Minecraft `mods` folder.
4. Launch Minecraft with the Fabric loader.

## Build from Source

```bash
git clone git@github.com:XodiumSoftware/randomblockplacement.git
cd randomblockplacement
just build
```

The built JAR is in `build/libs/`.

## Development Commands

Uses [just](https://github.com/casey/just) as the command runner.

| Command       | Description                       |
| ------------- | --------------------------------- |
| `just build`  | Build the mod JAR                 |
| `just run`    | Run the Minecraft client with mod |
| `just lint`   | Run ktlint                        |
| `just format` | Fix ktlint issues                 |
| `just dev`    | Build and run the client          |

## Configuration

The mod has no configuration file. All settings are compile-time constants inside the module objects.

## Contributing

Contributions are welcome! If you have suggestions, bug reports, or want to contribute code, please create an issue or a pull request.

1. Fork the repository.
2. Create a new branch for your feature or fix.
3. Commit your changes.
4. Push to your forked repository.
5. Open a pull request to the main repository.

---
