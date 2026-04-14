# UUID v7 Generator for IntelliJ IDEA

[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

Generate time-ordered **UUID v7** directly in your editor with a single shortcut.

UUID v7 is a time-based unique identifier that is lexicographically sortable, making it ideal for database primary keys,
distributed systems, and event sourcing.

## ✨ Features

- **One-click generation** — Insert UUID v7 via the Generate popup (`Alt+Insert` / `Cmd+N`)
- **Multiple carets support** — Generate UUIDs at all caret positions simultaneously
- **No external dependencies** — Uses Kotlin standard library's `Uuid.generateV7()`
- **Undo/Redo support** — Fully integrated with IntelliJ's undo system
- **RFC 9562 compliant** — Implements the official UUID v7 specification

## 📦 Installation

### From JetBrains Marketplace

1. Open **Settings** → **Plugins** → **Marketplace**
2. Search for **"UUID v7 Generator"**
3. Click **Install**
4. Restart the IDE

### Manual Installation

1. Download the latest release from [GitHub Releases](https://github.com/EgorBratuhin/idea-uuid-v7-generator/releases)
2. Open **Settings** → **Plugins** → **⚙️** → **Install Plugin from Disk...**
3. Select the downloaded `.zip` file
4. Restart the IDE

## 🚀 Usage

1. Place your cursor where you want to insert a UUID v7
2. Press `Alt+Insert` (Windows/Linux) or `Cmd+N` (macOS)
3. Select **UUID v7** from the Generate menu
4. A time-ordered UUID v7 will be inserted at the cursor position

### Example Output

`019d8bcf-7187-7006-8197-8a98b7cc6cbe`

### Multiple Carets

The plugin supports IntelliJ's multiple carets feature. Simply place multiple carets using `Alt+Click` or `Ctrl+Alt+Shift+Click`,
then trigger the action — UUIDs will be generated at all caret positions.

## 🔧 Requirements

- IntelliJ IDEA 2025.2 or newer (or any IntelliJ-based IDE)
- Kotlin plugin (bundled with IntelliJ IDEA)

## 🛠️ Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/EgorBratuhin/idea-uuid-v7-generator.git
cd idea-uuid-v7-generator

# Build the plugin
./gradlew buildPlugin

# Run the plugin in a sandbox IDE
./gradlew runIde
```
