package com.kevin.intents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import com.kevin.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.nextScreenBtn.setOnClickListener {
            navigateNextScreen()
        }

        binding.webBrowserBtn.setOnClickListener {
            openBrowser()
        }

        binding.callBtn.setOnClickListener {
            makeCall()
        }

        binding.galleryBtn.setOnClickListener {
            openGallery()
        }

        binding.alarmBtn.setOnClickListener {
            setAlarm()
        }

        binding.cameraBtn.setOnClickListener {
            openCamera()
        }


    }

    private fun setAlarm() {
        val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        startActivity(intent)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_VIEW)
        startActivity(intent)
    }

    private val CAMERA_PERMISSION_CODE = 10
    private val CALL_PERMISSION_CODE = 11

    private fun makeCall() {

        val phone = "+13612532921"
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phone")

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(callIntent)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_CODE
            )
        }

    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(cameraIntent)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }
    }

    private fun openBrowser() {
        val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        startActivity(browser)
    }

    private fun navigateNextScreen() {
        val switcher = Intent(this, SecondActivity::class.java)
        startActivity(switcher)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CALL_PERMISSION_CODE) {
            makeCall()
        }
        if (requestCode == CAMERA_PERMISSION_CODE) {
            openCamera()
        }
    }
}