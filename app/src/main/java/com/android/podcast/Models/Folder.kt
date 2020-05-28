package com.android.podcast.Models

enum class TYPE {
    FOLDER, AUDIO, VIDEO, TEXT
}

class Folder {

    var folders: MutableList<Folder> = ArrayList()
    var video: MutableList<Video> = ArrayList()
    var audios: MutableList<Audio> = ArrayList()
    var files: MutableList<TextFile> = ArrayList()

    var image: String = "" //path of folder image
    var id:String =""
    var parentFolderId:String=""

    var itemType:TYPE = TYPE.FOLDER
}
