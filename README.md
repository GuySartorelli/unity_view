# unity_view

Flutter plugin to expose native UnityPlayer views to as a Flutter widget

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.io/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our
[online documentation](https://flutter.io/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.


Initially this plugin will be for android only. It will include helper methods for sending data between unity and flutter using MethodChannels. The iOS implementation requires more steps for copying Unity files into native code, which I would like to automate. As such I am tackling the main challenges of having a working concept first in Android.
I intend to include full instructions on how to set this up from both the unity any flutter end, including additional instructions for Vuforia AR projects. I also intend to automate the process of building the unity project out as a library (aar file) instead of as an apk if (A) that's possible and (B) hasn't already been done.

~~Currently the plan is to use Javassist to add the UnityPlayer as a field to the MainActivity and override some methods necessary to get the UnityPlayer to render and know when to pause, etc. The intention there is to remove any need for the programmer to fiddle around wtih native code to get the plugin working. Ideally they should be able to simply declare the location of the Unity build and build the UnityView widget on the flutter end. This does require some assumptions (such as the programmer has not renamed the MainActivity class, nor already overridden those methods (although in a future version I will be more careful to simply append to those methods rather than fully reimplement them), etc. These assumptions should be safe for almost all situations, and where they are not true I expect the programmer will be sufficiently skilled to amend the plugin source code to their purposes.~~  
It turns out Javassist is not compatible with Android' JVM, and probably with the apk file in general for that matter. As such I will not be able to avoid adding the necessary mUnityPlayer field and overridden methods to the MainActivity class source code directly.  
My intention at this stage is to have an executable (in a similar fashion as the [flutter_launcher_icons](https://pub.dartlang.org/packages/flutter_launcher_icons) package. Upon execution, it will add the appropriate field and overridden methods. This will have the added benefit of allowing the programmer to rename the MainActivity if they choose (and assuming Flutter allows that), and not necessarily replacing those methods by default if the programmer has already overridden them in that class. It also means having a similar experience for both the android and iOS sides will be even easier, as whatever equivalent changes are needed on the iOS side can be made in that same execution.
