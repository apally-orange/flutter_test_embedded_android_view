import Flutter
import UIKit

public class SwiftFlutterTestEmbeddedAndroidViewPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_test_embedded_android_view", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterTestEmbeddedAndroidViewPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
