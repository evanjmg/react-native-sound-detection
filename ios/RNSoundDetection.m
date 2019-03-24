
#import "RNSoundDetection.h"

@implementation RNSoundDetection

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()
RCT_EXPORT_METHOD((NSString *)path, getTracks:(RCTResponseSenderBlock)callback) {
    AVAsset *asset = [AVAsset assetWithURL:path];
    NSArray *audioTracks = [asset tracksWithMediaType:AVMediaTypeAudio]
    callback(@[[NSNull null], audioTracks]);
}

@end
