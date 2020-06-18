
Pod::Spec.new do |s|
  s.name         = "RNSoundDetection"
  s.version      = "1.0.0"
  s.summary      = "RNSoundDetection"
  s.description  = <<-DESC
                  RNSoundDetection
                   DESC
  s.homepage     = "https://github.com/evanjmg/react-native-sound-detection"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNSoundDetection.git", :tag => "master" }
  s.source_files  = "RNSoundDetection/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
