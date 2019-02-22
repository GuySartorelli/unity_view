package unityview.com.unityview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.unity3d.player.UnityPlayer;
import com.vuforia.DataSet;
import com.vuforia.ObjectTracker;
import com.vuforia.STORAGE_TYPE;
import com.vuforia.TrackerManager;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import static io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.platform.PlatformView;

public class UnityView implements PlatformView{//}, MethodCallHandler {
    private static UnityPlayer unityPlayer;
    public static void setUnityPlayer(UnityPlayer player){
        unityPlayer = player;
    }

    private final String CHANNEL = "unityview.com.unityview/unityview_";
    private final MethodChannel methodChannel;
    private Context context;
//    private String xmlPath;

    UnityView(Context context, BinaryMessenger messenger, int id) {
        this.context = context;
        methodChannel = new MethodChannel(messenger, CHANNEL + id);
        //methodChannel.setMethodCallHandler(this);
    }

    @Override
    public View getView() {
        if (unityPlayer != null) { //TODO try using new UnityPlayer(context)
            unityPlayer.start();
            unityPlayer.requestFocus();
            return unityPlayer;
        } else {
            TextView noUnityPlayer = new TextView(context);
            noUnityPlayer.setText("no UnityPlayer found");
            return noUnityPlayer;
        }
    }

    //TODO this is to send methods to unity I guess
//    @Override
//    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
//        switch (call.method) {
//            case "setMarker":
//                setMarker((String)call.arguments);
//                result.success(null);
//                break;
//            default:
//                result.notImplemented();
//        }
//    }

//    private void setMarker(String xmlPath) {
//        System.out.println("RECEIVED XMLPATH FROM FLUTTER: " + xmlPath);
//        this.xmlPath = xmlPath;
//    }

//    public void loadMarkers(){
//        if (DataSet.exists(this.xmlPath, STORAGE_TYPE.STORAGE_ABSOLUTE)){
//            System.out.println("\n\nDATASET EXISTS!\n\n");
////            ObjectTracker objectTracker = (ObjectTracker)TrackerManager.getInstance().getTracker(ObjectTracker.getClassType());
////            DataSet dataSet = objectTracker.createDataSet();
////            dataSet.load(this.xmlPath, STORAGE_TYPE.STORAGE_ABSOLUTE);
////            objectTracker.activateDataSet(dataSet);
////            dataSet.createTrackable()
//            UnityPlayer.UnitySendMessage("AndroidCommunicationManager", "LoadMarkers", this.xmlPath);
//        } else {
//            System.out.println("\n\nNO DATASET LOADED; CHECK VALIDITY OF VUFORIA DATABASE FILES\n\n"); //TODO a user friendly error should be displayed
//        }
//    }

    @Override
    public void dispose() {
        //TODO consider making this optionally call another method (e.g. quit)?
        unityPlayer.stop();
//        unityPlayer.quit();
    }
}
