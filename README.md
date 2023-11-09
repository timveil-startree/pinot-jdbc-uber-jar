# Apache Pinot JDBC Driver "uber" Jar

## Overview
The [Apache Pinot](https://github.com/apache/pinot) project includes a JDBC Driver but does not publish a `standalone` or `uber` jar for the `pinot-jdbc-client` module.  This project attempts make it simpler to include the Pinot JDBC Driver, and its transitive dependencies, into other projects or tools.  

In addition to simply creating a `shaded` driver jar, an effort has been made to streamline the dependency graph by removing large and or unnecessary dependencies. The original `shaded` jar provided by the Pinot project for version `1.1.0` was `107.5 MB`.  With the changes in this project, this `uber` jar for the same Pinot version is down to `67.1 MB`... nearly a *40%* reduction in size!

## How to Build
To build and install locally, you must have Maven installed and properly configured.  After that, it's as simple as running `mvn clean install`.  A file called `pinot-jdbc-uber-x.jar` will be created in your `target` directory and also installed into your local Maven repository.  The newly created jar will have the Pinot JDBC driver as well as all required dependencies.

Once built, you can refer to this jar in a Maven project with the following...
```xml
<dependency>
    <groupId>org.apache.pinot</groupId>
    <artifactId>pinot-jdbc-uber-jar</artifactId>
    <version>1.1.0-SNAPSHOT</version>
</dependency>
```

## Other Helpful Commands

### Building Pinot
Quickly build and install only the JDBC Client and its dependents from the Apache Pinot project to you local Maven repository.  This is useful for quick testing of the "uber" jar.
```shell
# Linux/macOS
mvn clean install -DskipTests -Dcheckstyle.skip=true -pl pinot-clients/pinot-jdbc-client -am

# Windows (Maven Daemon)
mvnd clean install -DskipTests -D"checkstyle.skip"=true -pl pinot-clients/pinot-jdbc-client -am
```

In addition to skipping the `checkstyle` plugin, you may also skip the following: `-Djacoco.skip=true -Drat.skip=true -Dlicense.skip=true`

### Dependency and Plugin Version Management
To output the full dependency tree to a file you can run something like this:
```shell
mvn dependency:tree -DoutputFile=/some/path/tree.log -DappendOutput=true
```

to locate a specific dependency you can run:
```shell
mvn dependency:tree -DappendOutput=true -Dincludes=org.apache.helix:helix-core
```

to check for plugin updates you can run:
```shell
mvn versions:display-plugin-updates
```

to check for dependency updates you can run:
```shell
mvn versions:display-dependency-updates "-Dmaven.version.ignore=.*-M.*,.*-alpha.*,.*Alpha.*,.*rc.*"
```