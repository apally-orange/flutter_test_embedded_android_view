import 'package:flutter/material.dart';
import 'package:flutter_test_embedded_android_view/flutter_test_embedded_android_view.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: TestViewPlugin(),
        ),
      ),
    );
  }
}
