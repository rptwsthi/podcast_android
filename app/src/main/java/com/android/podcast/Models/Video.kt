package com.android.podcast.Models

import java.io.File

class Video {
    var name:String=""

    var description=""

//    var file: File?
//        get() {
//            TODO()
//        }
//        set(value) {}

    var image: String = "" //path of image
    var videoPath: String = "" //path of video

    var itemType:TYPE = TYPE.VIDEO
    var parentFolderId:String=""
}