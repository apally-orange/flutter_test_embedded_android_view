import 'dart:io';

import 'package:flutter/material.dart';

class TestViewPlugin extends StatelessWidget {
  const TestViewPlugin({Key key,})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    if (Platform.isAndroid) {
      return AndroidView(
        viewType: 'test_channel',
        onPlatformViewCreated: _onPlatformViewCreated,
      );
    } else {
      // No implemented
      return Container();
    }
  }

  void _onPlatformViewCreated(int id) {
  }
}
