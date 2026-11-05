# randomblockplacement task runner
# Install `just` once: https://github.com/casey/just

[windows]
set shell := ["powershell.exe", "-NoProfile", "-Command"]

_default:
    @just --list

# ── Gradle wrapper (cross-platform) ──

_gradle := if os() == "windows" { ".\\gradlew.bat" } else { "./gradlew" }

# Build the mod JAR
build:
    {{ _gradle }} build

# Run the Minecraft client with the mod
[unix]
run *args:
    {{ _gradle }} runClient {{ replace(args, ' --gradle-args=', ' --args=') }}

[windows]
run *args:
    {{ _gradle }} runClient {{ args }}

# Run ktlint
lint:
    {{ _gradle }} ktlintCheck

# Fix ktlint issues
format:
    {{ _gradle }} ktlintFormat

# Build and run the client
dev *args: build (run args)
