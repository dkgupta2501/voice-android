# Click To Call Android SDK (Cloud Telephony)
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
  implementation 'com.github.mobtexting:voice-android:v1.0.1'
}
```
#### Define _API KEY_ in Manifest file inside Application tag
```xml
  <meta-data android:name="mobtexting.api_key" android:value="@string/mobtextingapikey" />
```
#### Usage (How to perform click to call)
_Pass Pilot number, caller number and receiver number as parameter_
```java
public class MainActivity extends AppCompatActivity implements MobtextingInterface{
    private Mobtexting mobtexting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create instance of mobtexting and pass parameter as Pilot number, caller number and receiver number
        mobtexting=new Mobtexting(this);
        mobtexting.clickToCall("8030********","8553******","7250******",this);
    }

    @Override
    public void onResponse(ServerResponse serverResponse) {
        Log.d("success",serverResponse.getResponseCode()+"    "+serverResponse.getMessage()+"  "
        +serverResponse.getStatus());
    }

    @Override
    public void onError(ServerResponse serverResponse) {
        Log.d("failure",serverResponse.getResponseCode()+" "+serverResponse.getMessage()+"  "+
        serverResponse.getStatus());
    }
}
```
#### Note: _Add Internet persmission in android mainfest file_
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
