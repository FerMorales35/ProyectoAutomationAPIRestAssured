# Automatizacion API

##**Pre-requisites:**
* Java 1.8 (JDK)
* Gradle
* Intellij IDEA Community Edition

##**Run tests:**
```
gradle clean test
```

##**Run test in a specific environment:**
```
gradle clean test -Denv=prod
```

##**Run test and generate report:**
```
./gradlew clean test
./gradlew allureReport
./gradlew allureServe
```

##**Run just one test:**
```
gradle clean test --tests gradle clean test --tests TestAPIBrewery.getListadoCervecerias
gradle clean test --tests gradle clean test --tests TestAPIBrewery.getCerveceriaLagunitas
gradle clean test --tests gradle clean test --tests TestAPIBrewery.validarNombreCerveceria
```

