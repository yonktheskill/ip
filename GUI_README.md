# Evan Chatbot GUI

## What Was Implemented

A complete JavaFX GUI implementation for the Evan chatbot with the following features:

### Components Created

1. **Main.java** - JavaFX application entry point that launches the GUI
2. **MainWindow.java** - Main controller handling user interactions and chat display
3. **DialogBox.java** - Reusable chat message component (follows OOP principles)
4. **MainWindow.fxml** - Responsive layout definition using FXML
5. **styles.css** - Modern, clean styling with gradient effects

### Features

- **Responsive Design**: Components resize properly with the window
- **Modern UI**: Clean gradients, rounded corners, and smooth shadows
- **Reusable Components**: DialogBox can be used for both user and bot messages
- **OOP Principles**: Separation of concerns, encapsulation, proper inheritance
- **Graceful Image Handling**: Creates colored placeholder circles if images are missing
- **Auto-scrolling**: Chat automatically scrolls to show latest messages
- **Smooth Exit**: 2-second delay after "bye" command before closing

### Visual Design

- **User messages**: Purple gradient bubbles (right-aligned)
- **Evan messages**: White bubbles with subtle border (left-aligned)
- **Input field**: Rounded with focus effects
- **Send button**: Gradient purple with hover animations
- **Avatar images**: Circular clipped (50x50 display size)

## How to Run

### Option 1: Using Gradle (Recommended)

```bash
./gradlew run
```

### Option 2: Build and Run JAR

```bash
./gradlew shadowJar
java -jar build/libs/duke.jar
```

### On Windows

```bash
gradlew.bat run
```

## Images Needed

The application will run with colored placeholder circles, but for better appearance:

### 1. User Avatar (`user.png`)

- **Location**: `src/main/resources/images/user.png`
- **Size**: 100x100 pixels (PNG with transparency)
- **Suggestions**: User icon, profile picture, silhouette
- **Download from**: flaticon.com, iconscout.com, or use your own image

### 2. Evan Avatar (`evan.png`)

- **Location**: `src/main/resources/images/evan.png`
- **Size**: 100x100 pixels (PNG with transparency)
- **Suggestions**: Robot icon, bot avatar, friendly AI mascot
- **Download from**: flaticon.com, iconscout.com

## Technical Details

### Dependencies Added

- JavaFX 17.0.2 (Controls & FXML)
- OpenJFX Gradle Plugin 0.0.13

### Architecture

- **MVC Pattern**: Model (TaskList, Task), View (FXML), Controller (MainWindow)
- **Command Pattern**: All commands return String responses
- **Singleton-like**: Evan instance manages application state
- **Component Reusability**: DialogBox used for all messages

### Responsive Features

- Minimum window size: 400x600
- DialogBox max width: 280px (prevents overly wide messages)
- ScrollPane binds to container height (auto-scroll)
- Input area anchored to bottom

## Customization

### Change Colors

Edit `src/main/resources/view/styles.css`:

- User bubble: `.user-dialog .dialog-text { -fx-background-color: ... }`
- Button: `.button { -fx-background-color: ... }`

### Change Window Size

Edit `src/main/java/duke/Main.java`:

- `stage.setMinHeight(600)`
- `stage.setMinWidth(400)`

### Modify Layout

Edit `src/main/resources/view/MainWindow.fxml`:

- Adjust spacing, padding, anchors

## Original CLI Mode

The original CLI mode is still available by running:

```java
java duke.Evan
```

## File Structure

```
src/
├── main/
│   ├── java/duke/
│   │   ├── Main.java              (GUI entry)
│   │   ├── Evan.java              (Core logic)
│   │   ├── MainWindow.java        (GUI controller)
│   │   ├── DialogBox.java         (Chat component)
│   │   ├── Parser.java
│   │   ├── Storage.java
│   │   ├── TaskList.java
│   │   ├── Ui.java
│   │   ├── command/               (All commands)
│   │   ├── exception/
│   │   └── task/
│   └── resources/
│       ├── view/
│       │   ├── MainWindow.fxml    (Layout)
│       │   └── styles.css         (Styling)
│       └── images/
│           ├── user.png           (Your image here)
│           └── evan.png           (Your image here)
```
