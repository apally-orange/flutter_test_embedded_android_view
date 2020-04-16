#import "FlutterTestEmbeddedAndroidViewPlugin.h"
#if __has_include(<flutter_test_embedded_android_view/flutter_test_embedded_android_view-Swift.h>)
#import <flutter_test_embedded_android_view/flutter_test_embedded_android_view-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_test_embedded_android_view-Swift.h"
#endif

@implementation FlutterTestEmbeddedAndroidViewPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterTestEmbeddedAndroidViewPlugin registerWithRegistrar:registrar];
}
@end
