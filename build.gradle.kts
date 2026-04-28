import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.3.21"
    id("org.jetbrains.intellij.platform") version "2.15.0"
}

group = "by.bratukhin"
version = "1.0.2"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdea("2025.2.4")
        testFramework(TestFrameworkType.Platform)

        bundledPlugin("org.jetbrains.kotlin")
    }
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.assertj:assertj-core:3.27.7")
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "252"
        }

        changeNotes = """
            <h3>1.0.2</h3>
            <ul>
                <li>Replace selected text with UUID v7 when a selection exists</li>
                <li>Updated Gradle 9.0.0 -> 9.4.1</li>
                <li>Updated Kotlin 2.3.20 -> 2.3.21</li>
                <li>Updated IntelliJ Platform Gradle Plugin 2.14.0 -> 2.15.0</li>
            </ul>
            <h3>1.0.1</h3>
            <ul>
                <li>Added 'UUID v7' action to Generate menu (Alt+Insert / Cmd+N)</li>
                <li>RFC 9562 compliant UUID v7 generation</li>
                <li>Support for multiple carets</li>
            </ul>
        """.trimIndent()
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}
