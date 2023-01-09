package com.example.pickimagefromgallery

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.todo.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(isPermissionGranted()){
            getPhoto()
        }
        else{
            requestLocationPermissionFromUser()
        }
    }

    private fun getPhoto() {
        TODO("Not yet implemented")
    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                getPhoto()

            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(this,"can't App work without permission ", Toast.LENGTH_LONG).show()
            }
    }

    private fun requestLocationPermissionFromUser() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_IMAGES)){
            showDialoge(message = "app needs to access your gallery ",
                posActionName = "ok",
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    // request the permission -> system
                    requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)

                    dialog.dismiss()
                },
                negActionName = "no",
                negAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        }
        else{
            requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
        }
    }

    //to check whether location is granted or not
    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_MEDIA_IMAGES)== PackageManager.PERMISSION_GRANTED
        //&& ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED

    }




}