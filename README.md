# Click To Call Android SDK (cloud telephony)
_Easy to integrate android sdk to perform click to call operation from Mobtexting_
## Getting Started
### Gradle
**Step 1.** _Add the JitPack repository to your build file_
```java
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
**Step 2.** Add the dependency
```java
dependencies {
  implementation 'com.github.mobtexting:voice-android:v1.0.0'
}
```
#### define _API KEY_ in Manifest file inside Application tag
```xml
  <meta-data android:name="mobtexting.api_key" android:value="@string/mobtextingapikey" />
```
