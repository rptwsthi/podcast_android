package com.android.podcast.Models

import java.io.File

class Audio {

    var name:String=""

    var description:String=""

//    var file: File?
//        get() {
//            TODO()
//        }
//        set(value) {}

    var image: String = "" //path of image
    var audioPath: String = "" //path of video

    var itemType:TYPE = TYPE.AUDIO
    var parentFolderId:String=""

}