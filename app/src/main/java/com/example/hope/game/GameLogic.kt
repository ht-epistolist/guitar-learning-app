package com.example.hope.game

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import com.example.hope.Manifest


val audioSource = MediaRecorder.AudioSource.MIC
val sampleRate = 44100 //initializes the sample rate
val channelConfig = AudioFormat.CHANNEL_IN_MONO //initializes mono audio
val audioFormat = AudioFormat.ENCODING_PCM_16BIT
val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
val buffer = ShortArray(bufferSize)
/*
val audioRecord = AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, bufferSize) //initialize audio recorder with given parameters
audioRecord.startRecording()
Thread(Runnable{
    var ii = 0
    while(ii != 100){
        ii++
        val read = audioRecord.read(buffer, 0, buffer.size) //write into buffer
    }
}).start()

audioRecord.stop()
audioRecord.release()
*/
