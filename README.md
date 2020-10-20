# PojoBuilder
![License](https://img.shields.io/github/license/Maowcraft/PojoBuilder)
![Downloads](https://img.shields.io/github/downloads/Maowcraft/PojoBuilder/latest/total)
![Maven Version](https://img.shields.io/jitpack/v/github/maowcraft/pojobuilder)

A "Plain Old Java Object" class builder.

## Overview

This is a utility library used to generate POJOs (Plain Old Java Objects). A POJO is typically a small class used for (de)serialization or other basic utilities.

### Features

* Create a POJO easily with a Builder class.
* Write the POJO to Java source.
* Serialize and deserialize your POJOs to/from Json.

## Setup

1. Add the Jitpack repository to your `build.gradle` file, if you don't have a Gradle build file then... what are you doing with your life?
```
repositories {
  ...
  maven { url 'https://jitpack.io' }
}
```
2. Add PojoBuilder to your dependencies and then refresh Gradle.
```
dependencies {
  implementation 'com.github.maowcraft:pojobuilder:<insert version here>'
}
```

## Usage
### Creating a POJO
#### Method 1: PojoObject.Builder

To store values: Create a POJO builder, set your fields, and then build it:
```java
PojoObject object = new PojoObject.Builder().addField("yourField", "Field value").build();
```
PojoObjects created via Builder are immutable, meaning that their values can never be changed; however, you can still get values from them like so:
```java
String value = object.getField("yourField");
```

#### Method 2: PojoObject.from();

To store values: Here's an example of a JSON file that you can use to create a POJO from:
```json
{
  "field": "test",
  "array": [
    "test1",
    "test2"
  ]
}
```
To create the POJO, just do like so:
```java
PojoObject object = PojoObject.from("<path to json file>");
```
Getting a value from a POJO this way is still the same as with a Builder.

### Saving a POJO
#### Method 1: PojoObject.to();

You can write a POJO to a JSON file like so:
```java
PojoObject object = ...;
object.to("<path to json file>");
```

#### Method 2: PojoWriter.toSource();

To write a POJO to a Java source file, you need to call the method `PojoWriter.toSource`:
```java
PojoObject object = ...;
PojoWriter.toSource("<package name>", "<class name>", object, "<path to save file in>");
```
Using the example PojoObject created using the JSON file above, we get a file like this:
```java

package example;

import java.util.ArrayList;

public class ExamplePojo {

    private final String field;
    private final ArrayList array;

    public ExamplePojo(String field, ArrayList array) {
        this.field = field;
        this.array = array;
    }

    public String getField() {
        return field;
    }

    public ArrayList getArray() {
        return array;
    }

}
```
You might have to fix certain warnings yourself, such as raw parameterization.
