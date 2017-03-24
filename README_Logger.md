[![](https://jitpack.io/v/Ayvytr/logger.svg)](https://jitpack.io/#Ayvytr/logger)

<img align="right" src='https://github.com/ayvytr/logger/blob/master/images/logger-logo.png' width='128' height='128'/>

## [点击返回](https://github.com/Ayvytr/EasyAndroid)

#Logger
###Simple, pretty and powerful logger for android

##Logger provides :
	- Thread information
	- Class information
	- Method information
	- Pretty-print for json content
	- Pretty-print for new line "\n"
	- Clean output
	- Jump to source

## Gradle Build

###Step 1. Add the JitPack repository to your build file
####Add it in your root build.gradle at the end of repositories:
	allprojects {
			repositories {
				...
				maven { url "https://jitpack.io" }
			}
		}
###Step 2. Add the dependency
	dependencies {
		        compile 'com.github.Ayvytr:logger:1.18'
		}

### Current Log system
```java
Log.d(TAG,"hello");
```

<img src='https://github.com/ayvytr/logger/blob/master/images/current-log.png'/>


### Logger
```java
L.d("hello");
L.d("hello", "world", 5);   
```
<img src='https://github.com/ayvytr/logger/blob/master/images/description.png'/>

```java
L.d("hello");
L.e("hello");
L.w("hello");
L.v("hello");
L.wtf("hello");
L.json(JSON_CONTENT);
L.xml(XML_CONTENT);
L.log(DEBUG, "tag", "message", throwable);
```

#### Array, Map, Set and List are supported
```java
L.d(list);
L.d(map);
L.d(set);
L.d(new String[]);
```

### Change TAG
All logs
```java
L.init(YOUR_TAG);
```
Log based
```java
L.t("mytag").d("hello");
```
<img src='https://github.com/ayvytr/logger/blob/master/images/custom-tag.png'/>


### Settings (optional)
Change the settings with init. This should be called only once. Best place would be in application class. All of them
 are optional. You can just use the default settings if you don't init Logger.
```java
L
  .init(YOUR_TAG)                 // default PRETTYLOGGER or use just init()
  .methodCount(3)                 // default 2
  .hideThreadInfo()               // default shown
  .logLevel(LogLevel.NONE)        // default LogLevel.FULL
  .methodOffset(2)                // default 0
  .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter
}

```
Note: Use LogLevel.NONE for the release versions.

### Use another log util instead of android.util.log
- Implement LogAdapter
- set it with init
```java
.logAdapter(new CustomLogAdapter())
```

### More log samples
```java
L.d("hello");
L.e(exception, "message");
L.json(JSON_CONTENT);
```
<img src='https://github.com/ayvytr/logger/blob/master/images/logger-log.png'/>

### Method info
Observe the caller methods in the order they are invoked and also thread information.
```java
void methodA(){
   methodB();
}
void methodA(){
   L.d("hello");
}
```
Both method information will be shown in the order of invocation.

<img src='https://github.com/ayvytr/logger/blob/master/images/two-method-with-thread-desc.png'/>

### Change method count (Default: 2)
All logs
```java
L.init().methodCount(1);
```
Log based
```java
L.t(1).d("hello");
```

<img src='https://github.com/ayvytr/logger/blob/master/images/one-method-with-thread.png'/>

### Change method stack offset (Default: 0)
To integrate logger with other libraries, you can set the offset in order to avoid that library's methods.
```java
L.init().methodOffset(5);
```

### Hide thread information
```java
L.init().methodCount(1).hideThreadInfo();
```

<img src='https://github.com/ayvytr/logger/blob/master/images/one-method-no-header.png'/>

### Only show the message
```java
L.init().methodCount(0).hideThreadInfo();
```

<img src='https://github.com/ayvytr/logger/blob/master/images/just-content.png'/>

### Pretty print json, Logger.json
Format the json content in a pretty way
```java
L.json(YOUR_JSON_DATA);
```

<img src='https://github.com/ayvytr/logger/blob/master/images/json-log.png'/>

### Log exceptions in a simple way
Show the cause of the exception
```java
L.e(exception,"message");
```

### Notes
- Use the filter for a better result

<img src='https://github.com/ayvytr/logger/blob/master/images/filter.png'/>

- Make sure that the wrap option is disabled

<img src='https://github.com/ayvytr/logger/blob/master/images/wrap-closed.png'/>

### Timber Integration
You can also use logger along with Timber.
```java
Timber.plant(new Timber.DebugTree() {
  @Override protected void log(int priority, String tag, String message, Throwable t) {
    L.log(priority, tag, message, t);
  }
});
```

