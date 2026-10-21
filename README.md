<div id="readme-top"></div>

<h1 align="center">
  <br />
    <a href="https://xodium.org/">
        <img src="src\main\resources\assets\randomblockplacement\icon.png" alt="RandomBlockPlacement Logo" width="200">
    </a>
  <br /><br />
  RandomBlockPlacement
  <br />
  <br />
</h1>

<h4 align="center">A Minecraft Fabric client mod that randomly selects blocks from your hotbar when placing</h4><br />

<div align="center">

[![Issues][issues_shield_url]][issues_url]
[![License][license_shield_url]][license_url]

</div>

## Table of Contents

- [Guide](GUIDE.md)
- [Features](#features)
- [Requirements](#requirements)
- [Building](#building)
- [Built With](#built-with)
- [License][license_url]

## Features

- Toggle random block placement with a configurable key binding.
- HUD indicator showing whether random placement is active.
- Restricted to client-side only — works on any server.

## Requirements

- **Minecraft** 26.3
- **Fabric Loader** 0.19.5 or newer (or **Quilt Loader** 0.26.0 or newer)
- **Fabric Language Kotlin** 1.14.2 or newer
- **Fabric API** 0.164.0+ (Fabric) or **Quilted Fabric API** (Quilt)
- **Java** 25

Quilt support is metadata-ready: the same JAR can load on Quilt once `Quilted Fabric API` is available for Minecraft 26.3.

## Building

This project uses [just](https://github.com/casey/just) as its command runner. Run `just` to list all recipes.

```bash
just build    # Build the mod JAR (outputs in build/libs/)
just run      # Run the Minecraft client with the mod
just lint     # Run ktlint
just format   # Fix ktlint issues
just dev      # Build and run the client
```

## Built With

<div align="center">

[![Built With][built_with_shield_url]][built_with_url]

</div>

<p align="right"><a href="#readme-top">▲</a></p>

[built_with_shield_url]: https://skillicons.dev/icons?i=kotlin,gradle,github
[built_with_url]: https://skillicons.dev
[license_shield_url]: https://img.shields.io/github/license/XodiumSoftware/RandomBlockPlacement?style=for-the-badge
[license_url]: https://github.com/XodiumSoftware/RandomBlockPlacement?tab=readme-ov-file#License-1-ov-file
[issues_shield_url]: https://img.shields.io/github/issues/XodiumSoftware/RandomBlockPlacement?style=for-the-badge&color=yellow
[issues_url]: https://github.com/XodiumSoftware/RandomBlockPlacement/issues
