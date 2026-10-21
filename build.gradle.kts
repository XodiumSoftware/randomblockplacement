import org.gradle.api.tasks.SourceSet
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.18-SNAPSHOT"
    kotlin("jvm") version "2.4.21"
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
}

version = property("mod_version") as String
group = property("maven_group") as String

base {
    archivesName.set(property("archives_base_name") as String)
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

loom {
    splitEnvironmentSourceSets()

    mods {
        register("randomblockplacement") {
            sourceSet("main")
            sourceSet("client")
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
    compileOnly(kotlin("stdlib"))
}

java {
    withSourcesJar()
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

sourceSets {
    main {
        kotlin.setSrcDirs(listOf("src"))
        resources.srcDirs("src/main/resources")
    }
    named<SourceSet>("client") {
        kotlin.setSrcDirs(listOf("src/client"))
        resources.srcDirs("src/client/resources")
    }
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release = 25
    }
    withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_25
        }
    }
    jar {
        from("LICENSE.md") {
            rename { "${it}_${base.archivesName.get()}" }
        }
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    filter {
        exclude("**/build/**")
    }
}
