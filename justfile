set shell := ["sh", "-c"]

# Build the mod JAR
build:
    ./gradlew build

# Run the Minecraft client with the mod
run *args:
    ./gradlew runClient {{ args }}

# Run ktlint
lint:
    ./gradlew ktlintCheck

# Fix ktlint issues
format:
    ./gradlew ktlintFormat

# Build and run the client
dev *args: build (run args)
