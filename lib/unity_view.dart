import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void UnityViewCreatedCallback(UnityViewController controller);

//TODO check if this can be stateless; I think it can, and that'd simplify the code :D
class UnityView extends StatefulWidget {
  static const String _PACKAGE = 'unityview.com.unityview';
  final UnityViewCreatedCallback onViewCreation;
  final List<DeviceOrientation> viewOrientations;
  final List<DeviceOrientation> exitViewOrientations;

  const UnityView({Key key, this.onViewCreation, this.viewOrientations, this.exitViewOrientations}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _UnityViewState();

//  static Future<String> get platformVersion async {
//    final String version = await _channel.invokeMethod('getPlatformVersion');
//    return version;
//  }
}

class _UnityViewState extends State<UnityView> {
  Widget _alternative = Text("$defaultTargetPlatform is not yet supported by the unity_view plugin");
  BuildContext _context;

  @override
  Widget build(BuildContext context) {
    this._context = context;
    if (widget.viewOrientations != null) {
      SystemChrome.setPreferredOrientations(widget.viewOrientations);
    }

    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return AndroidView(
          viewType: '${UnityView._PACKAGE}/unityview',
          onPlatformViewCreated: _onPlatformViewCreated,
        );
      default:
        return this._alternative;
    }
  }

  void _onPlatformViewCreated(int id) {
    if (widget.onViewCreation == null) {
      return;
    }
    widget.onViewCreation(new UnityViewController(id, _context));
  }

  @override
  deactivate(){
    super.deactivate();
    if (widget.exitViewOrientations != null) {
      SystemChrome.setPreferredOrientations(widget.exitViewOrientations);
    }
  }
}

class UnityViewController {
  MethodChannel _outChannel;
  BuildContext _context;

  UnityViewController(int id, this._context) {
    _outChannel = new MethodChannel('${UnityView._PACKAGE}/unityview_$id');
  }

//  TODO replace this with a single method used for sending messages to unity
//  Future<void> setMarker(String xmlPath) async {
//    return _outChannel.invokeMethod('setMarker', xmlPath);
//  }
}