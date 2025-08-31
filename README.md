# DealHub

DealHub is a Java desktop application built with JavaFX and Maven that implements a simple auction/marketplace UI with user accounts, item rooms, posting, and a community chat server.

Key points
- Language: Java 20
- UI: JavaFX 20 (FXML)
- Build: Maven (includes javafx-maven-plugin)
- Database: MySQL (schema SQL available at `src/main/database/dealhub.sql`)

Quick start (Windows PowerShell)
1. Ensure Java 20 (JDK) is installed and JAVA_HOME is set.
2. Start MySQL and create the database. From MySQL shell or a client run the SQL in `src/main/database/dealhub.sql` to create tables and seed data.
3. From the project root run (uses the wrapper if available):

```
.\mvnw.cmd clean javafx:run
```

Or with system Maven:

```
mvn clean javafx:run
```

Notes for running
- The main JavaFX entrypoint is `com.dealhub.loginApplication` (module: `com.example.dealhub`).
- Database connection strings are set in controllers (e.g. `loginController`, `homeController`) as `jdbc:mysql://localhost:3306/dealhub` with username `root` and an empty password by default — update these to match your local MySQL credentials.
- If JavaFX runtime errors occur, make sure you run via the javafx-maven-plugin (shown above) or add JavaFX modules to the module path when running the jar.

Project structure (high level)
- `src/main/java/com/dealhub` — application classes and controllers (JavaFX controllers, models, app entrypoints).
- `src/main/resources/com/dealhub` — images and FXML resources used by the UI.
- `src/main/database/dealhub.sql` — database schema and seed data used by the app.

Important files
- `pom.xml` — Maven build and JavaFX plugin configuration.
- `module-info.java` — Java module exports and requires.
- `loginApplication.java` — JavaFX Application entry that loads `logIn.fxml`.
- `loginController.java`, `homeController.java` — controllers that handle user login and the home screen.

Features
- User registration and login (including an `admin` account handled in code).
- Home page that loads auction rooms and items dynamically from the DB.
- Item posting, profile editing, and a simple community chat server (separate controller/app files present).

Known limitations & quick suggestions
- Passwords are stored and compared as plain text in the database in this codebase. For production or public demos, use a secure hash (bcrypt/Argon2) and never store plain passwords.
- Database connection configuration is embedded in the controllers; extract to a config file or environment variables for easier deployment.

Where to go next
- Improve login security by switching to parameterized queries and hashed passwords.
- Externalize DB configuration to a properties file and add a simple script to create and seed the DB automatically.

If you need, I can also produce a one-page interview document (Word/Markdown) summarizing the project, architecture, and two technical challenges you faced and how you solved them.
