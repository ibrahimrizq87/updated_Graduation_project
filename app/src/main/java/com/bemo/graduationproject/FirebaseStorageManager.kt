package com.bemo.graduationproject

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage

class FirebaseStorageManager {
    private val mStorageRef = FirebaseStorage.getInstance().reference
    private lateinit var mProgressDialog:ProgressDialog
    fun uploadImage(mContext:Context,imageUri: Uri,userId:String){

        mProgressDialog= ProgressDialog(mContext)
        mProgressDialog.setMessage("please wait, image being upload....")
        mProgressDialog.show()
        val uploadTask = mStorageRef.child("users/$userId.png").putFile(imageUri)
        uploadTask.addOnSuccessListener {
            mProgressDialog.dismiss()

        }.addOnFailureListener{
            mProgressDialog.dismiss()
            Toast.makeText(mContext,"Successful operation ${it.printStackTrace()}",Toast.LENGTH_SHORT).show()

        }
    }
    fun downloadUri(userId:String): Uri? {
var uri:Uri? = null
        val downloadUriTask=mStorageRef.child("users/$userId.png").downloadUrl
        downloadUriTask.addOnSuccessListener {
       uri=it
        }.addOnFailureListener {
        // text required here
        }
return uri
    }
}