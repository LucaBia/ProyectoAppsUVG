package com.example.gianlucariverabiagioni.proyectoapps.dbhelper

import java.io.*

object BytesUtil {

    // toByteArray and toObject are taken from: http://tinyurl.com/69h8l7x
    @Throws(IOException::class)
    fun toByteArray(obj: Any): ByteArray? {
        var bytes: ByteArray? = null
        var bos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            bos = ByteArrayOutputStream()
            oos = ObjectOutputStream(bos)
            oos!!.writeObject(obj)
            oos!!.flush()
            bytes = bos!!.toByteArray()
        } finally {
            if (oos != null) {
                oos!!.close()
            }
            if (bos != null) {
                bos!!.close()
            }
        }
        return bytes
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    fun toObject(bytes: ByteArray): Any? {
        var obj: Any? = null
        var bis: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null
        try {
            bis = ByteArrayInputStream(bytes)
            ois = ObjectInputStream(bis)
            obj = ois!!.readObject()
        } finally {
            if (bis != null) {
                bis!!.close()
            }
            if (ois != null) {
                ois!!.close()
            }
        }
        return obj
    }

    fun toString(bytes: ByteArray): String {
        return String(bytes)
    }
}