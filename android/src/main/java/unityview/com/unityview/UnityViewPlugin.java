package unityview.com.unityview;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** UnityViewPlugin */
public class UnityViewPlugin {//implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
//    final MethodChannel channel = new MethodChannel(registrar.messenger(), "unity_view");
//    channel.setMethodCallHandler(new UnityViewPlugin());
    registrar.platformViewRegistry().registerViewFactory("unityview.com.unityview/unityview", new unityview.com.unityview.UnityViewFactory(registrar.messenger()));
  }

//  @Override
//  public void onMethodCall(MethodCall call, Result result) {
//    if (call.method.equals("getPlatformVersion")) {
//      result.success("Android " + android.os.Build.VERSION.RELEASE);
//    } else {
//      result.notImplemented();
//    }
//  }
}
