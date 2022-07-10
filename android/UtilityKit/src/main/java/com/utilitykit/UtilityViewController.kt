package com.utilitykit

import android.Manifest
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.graphics.*
import android.location.*
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.OnSuccessListener
import org.json.JSONObject
import java.io.*
import java.util.*
import java.util.concurrent.Executor

open class UtilityViewController : AppCompatActivity() {
    
    //Camera And Gallery feature
    
    private val GALLERY = 1
    private val CAMERA = 2
    private val RESULT_CANCELED = 0
    private var onCaptureImage : ((String)->Unit)? = null
    private var onCaptureImageUrl : ((Uri?)->Unit)? = null
    private var onCaptureImageBitmap : ((Bitmap?)->Unit)? = null
    private  var pictureDialog : AlertDialog.Builder? = null
    var imageUri : Uri? = null
    private val IMAGE_DIRECTORY = "/demonuts"
    var selectedSource = -1
    
    fun getImage(compltetion:(String)->Unit){
        this.onCaptureImage = compltetion
        this.setupPermissions()
    }
    
    fun getImageUrl(compltetion:((Uri?)->Unit)){
        this.onCaptureImageUrl = compltetion
        this.setupPermissions()
    }
    
    fun getImageUrlFromCamera(compltetion:((Uri?)->Unit)){
        this.onCaptureImageUrl = compltetion
        selectedSource = 0
        this.setupPermissions()
    }
    fun getImageUrlFromGallery(compltetion:((Uri?)->Unit)){
        this.onCaptureImageUrl = compltetion
        selectedSource = 1
        this.setupPermissions()
    }
    
    fun getImageBitMap(compltetion:((Bitmap?)->Unit)){
        this.onCaptureImageBitmap = compltetion
        this.setupPermissions()
    }
    var activityIndicator : AlertDialog? = null
    val LOCATION_PERMISSION_ID = 42

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }



    fun alert(title: String,message:String){
        this.runOnUiThread {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setMessage(message)
            val dialogueListener  = DialogInterface.OnClickListener { dialog, which ->
                DialogInterface.BUTTON_POSITIVE
            }
            builder.setPositiveButton("Ok", dialogueListener)
            val dialog = builder.create()
            dialog.show()
        }
    }
    fun alert(title: String,message:String,completion:()->Unit){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        val dialogueListener  = DialogInterface.OnClickListener { dialog, which ->
            DialogInterface.BUTTON_POSITIVE
            completion()
        }
        builder.setPositiveButton("Ok", dialogueListener)
        val dialog = builder.create()
        dialog.show()
    }

    var scannedResult: String = ""


    fun toast(msg:String){
        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }

    fun toastLong(msg:String){
        Toast.makeText(applicationContext,msg,Toast.LENGTH_LONG).show()
    }

    fun startActivityIndicator() {
        val llPadding = 30
        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam

        val progressBar = ProgressBar(this)
        progressBar.isIndeterminate = false
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam

        llParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(this)
        tvText.text = "Loading ..."
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20f
        tvText.layoutParams = llParam

        ll.addView(progressBar)
        ll.addView(tvText)

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(ll)

        this.activityIndicator = builder.create()
        this.activityIndicator!!.show()
        val window = this.activityIndicator!!.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(this.activityIndicator!!.window!!.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            this.activityIndicator!!.window!!.attributes = layoutParams
        }
    }

    fun startActivityIndicator(message: String) {
        val llPadding = 30
        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam

        val progressBar = ProgressBar(this)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam

        llParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        llParam.gravity = Gravity.CENTER
        val tvText = TextView(this)
        tvText.text = message
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20f
        tvText.layoutParams = llParam

        ll.addView(progressBar)
        ll.addView(tvText)

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setView(ll)

        this.activityIndicator = builder.create()
        this.activityIndicator?.show()
        val window = this.activityIndicator!!.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(this.activityIndicator!!.window!!.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            this.activityIndicator!!.window!!.attributes = layoutParams
        }
    }
    fun stopActivityIndicator(){
        runOnUiThread {
            this.activityIndicator!!.dismiss()
        }
    }



   

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(ContentValues.TAG, "Permission to record denied")
            makeRequest()
        }else if(selectedSource == 0){
            takePhotoFromCamera()
        }else if (selectedSource == 1){
            choosePhotoFromGallary()
        }else{
            this.showPictureDialog()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(
                Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.LOCATION_HARDWARE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),
            this.CAMERA)
    }

    private fun showPictureDialog() {
        pictureDialog = AlertDialog.Builder(this)
        pictureDialog?.setTitle("Select")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog?.setItems(pictureDialogItems
        ) { dialog, which ->

            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog?.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera")
        imageUri = this.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
        )
        galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        ActivityCompat.startActivityForResult(this, galleryIntent, GALLERY, null)
    }


    private fun takePhotoFromCamera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        val values = ContentValues()
//        values.put(MediaStore.Images.Media.TITLE, "New Picture");
//        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
//        imageUri = this.getContentResolver().insert(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
//        ActivityCompat.startActivityForResult(this, intent, CAMERA, null)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 2)
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data.data
                if(onCaptureImageUrl != null && data.data != null){
                    onCaptureImageUrl!!(data.data!!)
                    return;
                }
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    if(onCaptureImageBitmap != null){
                        onCaptureImageBitmap?.let { it(bitmap) }
                    }else if(onCaptureImage != null){
                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                        val b = baos.toByteArray()
                        var base64Img = Base64.encodeToString(b, Base64.DEFAULT)
                        if(base64Img == null){
                            base64Img = ""
                        }
                        onCaptureImage!!(base64Img)
                    }
                    super.onActivityResult(requestCode, resultCode, data)
                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                    super.onActivityResult(requestCode, resultCode, data)
                }
            }
        }
        else if (requestCode == CAMERA)
        {
            if (data != null && data.data != null) {
                val contentURI = data.data
                if(onCaptureImageUrl != null && data.data != null){
                    onCaptureImageUrl!!(data.data!!)
                    return;
                }
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
//                    val path = saveImage(bitmap)
                    //Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
                    if(onCaptureImageBitmap != null){
                        onCaptureImageBitmap?.let { it(bitmap) }
                    }else if(onCaptureImage != null){
                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                        val b = baos.toByteArray()
                        var base64Img = Base64.encodeToString(b, Base64.DEFAULT)
                        if (base64Img == null) {
                            base64Img = ""
                        }
                        onCaptureImage!!(base64Img)
                    }
                    super.onActivityResult(requestCode, resultCode, data)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                    super.onActivityResult(requestCode, resultCode, data)
                }

            }
            //Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
        }


//        this.uploadToServer(requestCode,data)
    }
//    fun getRealPathFromURI(contentUri: Uri): String {
//        val proj = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = query(this.activity!!.contentResolver,this.even
//        val column_index = cursor
//            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor.moveToFirst()
//        return cursor.getString(column_index)
//    }

    fun encode(imageUri: Uri): String {
        val input = this.contentResolver.openInputStream(imageUri)
        val o = BitmapFactory.Options()
        o.inJustDecodeBounds = true
        o.inSampleSize = 1 / 4

        val image = BitmapFactory.decodeStream(input , null, o)
        //encode image to base64 string
        val baos = ByteArrayOutputStream()
        image!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var imageBytes = baos.toByteArray()
        val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        //        return "data:image/png;base64," + imageString
        return imageString
    }

    fun encode(imageBitmap: Bitmap): String {
        //encode image to base64 string
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        var imageBytes = baos.toByteArray()
        val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
//        return "data:image/png;base64," + imageString
        return imageString
    }


    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .timeInMillis).toString() + ".jpg")
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.path),
                arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.absolutePath)

            return f.absolutePath
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }


    ////////////////////////////////////////
    //Location
    private var onCaptureLocation : ((JSONObject)->Unit)? = null
    private var locationManager : LocationManager? = null

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            var locationObject = JSONObject()
//            locationObject.put(Key.latitude, location.latitude)
//            locationObject.put(Key.longitude, location.latitude)
//            if (onCaptureLocation != null) {
//                stopActivityIndicator()
//                onCaptureLocation!!(locationObject)
//                onCaptureLocation = null
//            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            print(provider)
        }

        override fun onProviderEnabled(provider: String) {
            print(provider)
        }

        override fun onProviderDisabled(provider: String) {
            print(provider)
        }
    }

    fun getLocation(compltetion: (JSONObject) -> Unit) {
        if (isLocationAvailable()) {
            this.onCaptureLocation = compltetion
            val isLocationAlreadyEnabled = isLocationEnabled()
            if (!isLocationAlreadyEnabled) {
                requestLocationPermissions()
            } else {
                startFetchingLocation()
            }
        } else {
            this.stopActivityIndicator()
            alert(
                "Oops!",
                "Location service not available at the moment.Please try after some time"
            )
        }
    }

    fun startFetchingLocation() {
//        try {
//            // Request location updates
//            this.startActivityIndicator("Fetching Location")
//            locationManager?.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER,
//                0L,
//                0f,
//                locationListener
//            )
//        } catch (ex: SecurityException) {
//            this.stopActivityIndicator()
//            alert("Oops!", "Security Exception, no location available")
//        }
    }


    private fun isLocationEnabled(): Boolean {
        val locPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        return locPermission == PackageManager.PERMISSION_GRANTED
    }

    private fun isLocationAvailable(): Boolean {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_ID)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                if (this.onCaptureLocation != null) {
                    // Request location updates
                    startFetchingLocation()
                }
            }
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Payment
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    //App Update


    open val TAG: String = "AppUpdate"
    val REQUEST_UPDATE_CODE = 1
    lateinit var installStateUpdatedListener: InstallStateUpdatedListener
    lateinit var appUpdateManager: AppUpdateManager
    lateinit var playServiceExecutor: Executor


    private lateinit var mAppUpdateManager: AppUpdateManager
    private var appUpdateInfo = JSONObject()
    fun checkAppUpdate() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //appUpdateManager = AppUpdateManagerFactory.create(this)
            installStateUpdatedListener = InstallStateUpdatedListener { installState ->
                when (installState.installStatus()) {
                    InstallStatus.DOWNLOADED -> {
                        Log.d(TAG, "Downloaded")
                        updaterDownloadCompleted()
                    }
                    InstallStatus.INSTALLED -> {
                        Log.d(TAG, "Installed")
                        appUpdateManager.unregisterListener(installStateUpdatedListener)
                    }
                    else -> {
                        Log.d(TAG, "installStatus = " + installState.installStatus())
                    }
                }
            }
            appUpdateManager.registerListener(installStateUpdatedListener)

            val appUpdateInfoTask = appUpdateManager.appUpdateInfo
            appUpdateInfoTask.addOnSuccessListener(
                playServiceExecutor,
                OnSuccessListener { appUpdateInfo ->
                    when (appUpdateInfo.updateAvailability()) {
                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            val updateTypes =
                                arrayOf(AppUpdateType.FLEXIBLE, AppUpdateType.IMMEDIATE)
                            run loop@{
                                updateTypes.forEach { type ->
                                    if (appUpdateInfo.isUpdateTypeAllowed(type)) {
                                        appUpdateManager.startUpdateFlowForResult(
                                            appUpdateInfo,
                                            type,
                                            this,
                                            REQUEST_UPDATE_CODE
                                        )
                                        return@loop
                                    }
                                }
                            }
                        }
                        else -> {
                            Log.d(TAG, "updateAvailability = " + appUpdateInfo.updateAvailability())
                        }
                    }
                })
        }
    }

    private fun updaterDownloadCompleted() {

//    Snackbar.make(
//        findViewById(R.id.activity_main_layout),
//        "An update has just been downloaded.",
//        Snackbar.LENGTH_INDEFINITE
//    ).apply {
//        setAction("RESTART") { appUpdateManager.completeUpdate() }
//        show()
//    }
    }

}

fun String.decode(): String {
    try{
        return Base64.decode(this, Base64.DEFAULT).toString(charset("UTF-8"))
    }catch (e:Exception){
        return this
    }

}

fun String.encode(): String {
    try{
        return Base64.encodeToString(this.toByteArray(charset("UTF-8")), Base64.DEFAULT)
    }catch (e:Exception){
        return this
    }
}

fun ContentValues.jsonObject():JSONObject{
    var jsonObject = JSONObject()
    for (item in this.valueSet()) {
        jsonObject.put(item.key,item.value)
    }
    return  jsonObject
}

fun ContentValues.json():String{
    return this.jsonObject().toString()
}