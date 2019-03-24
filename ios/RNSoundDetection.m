
#import "RNSoundDetection.h"
#import <AVFoundation/AVAsset.h>
#import <AVFoundation/AVAssetTrack.h>

@implementation RNSoundDetection

- (dispatch_queue_t)methodQueue
{
    return dispatch_queue_create("com.evanjmg.RNSoundDetection", DISPATCH_QUEUE_SERIAL);
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(getTracks: (NSString *)videoPath
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject) {
    @try {
        NSURL *url = [NSURL URLWithString:videoPath];

        AVURLAsset *videoAsset = [AVURLAsset assetWithURL: url];

        NSArray<AVAssetTrack *> *audioTracks = videoAsset.tracks;
        NSMutableArray *result = [NSMutableArray array];

        if (audioTracks.firstObject) {
            for (AVAssetTrack *audioTrack in audioTracks) {
                BOOL hasVideo = [audioTrack hasMediaCharacteristic: AVMediaCharacteristicVisual];

                NSString * type = hasVideo ? @"video" : @"audio";

                NSMutableDictionary *trackDictionary = [NSMutableDictionary dictionary];
                [trackDictionary setObject: type forKey: @"type"];
                [trackDictionary setObject: audioTrack.languageCode forKey: @"language"];
                [result addObject:  trackDictionary];
            }
        }
        resolve(result);
    }
    @catch (NSException *exception) {
        NSString *domain = @"com.evanjmg.RNSoundDetection";
        int errorCode = 1;
        NSMutableDictionary *userInfo = [NSMutableDictionary dictionary];
        [userInfo setObject:exception.name
                     forKey:NSLocalizedDescriptionKey];

        // Populate the error reference.
        NSError *error = [[NSError alloc] initWithDomain:domain
                                            code:errorCode
                                        userInfo:userInfo];
        reject(@"getTracks failure", @"Reading tracks failed", error);
    }
}

@end
