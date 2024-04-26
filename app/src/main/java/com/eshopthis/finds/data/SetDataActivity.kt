package com.eshopthis.finds.data

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.eshopthis.finds.R
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class SetDataActivity : AppCompatActivity() {

    private var selectedUri: Uri? = null
    private lateinit var imageView: ImageView
    private lateinit var title: EditText
    private lateinit var price: EditText
    private lateinit var description: EditText
    private lateinit var db_Table: EditText
    private var bitmap: Bitmap? = null
    private lateinit var buttonPickImg: Button
    private lateinit var buttonUpload: Button
    private val url = "https://food-buzz.000webhostapp.com/upload_data.php"
    private var encodeImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_data)

        title = findViewById(R.id.upload_itemTitle_id)
        price = findViewById(R.id.upload_itemPrice_id)
        description = findViewById(R.id.upload_itemDescription_id)
        imageView = findViewById(R.id.upload_itemImage_id)
        buttonPickImg = findViewById(R.id.upload_chooseImageButton_id)
        buttonUpload = findViewById(R.id.upload_buttonUpload_id)
        db_Table = findViewById(R.id.dbTable)

        buttonPickImg.setOnClickListener { chooseImage() }

        buttonUpload.setOnClickListener { uploadImage() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            selectedUri = data.data
            try {
                val inputStream = contentResolver.openInputStream(selectedUri!!)
                bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)
                imageStore(bitmap!!)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

        data?.data?.let { uri ->
            try {
                imageView.setImageURI(uri)
                imageView.visibility = View.VISIBLE
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Error: $e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    private fun uploadImage() {
        val i_title = title.text.toString()
        val i_price = price.text.toString()
        val i_description = description.text.toString()
        val tName = db_Table.text.toString()

        val request = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                        title.setText("")
                        price.setText("")
                        description.setText("")
                        db_Table.setText("")
                        imageView.visibility = View.GONE

                        Toast.makeText(this@SetDataActivity, response.toString(), Toast.LENGTH_SHORT).show()
            },
        Response.ErrorListener { error ->
                Toast.makeText(this@SetDataActivity, error.message, Toast.LENGTH_SHORT).show()
        }
        ) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val param = hashMapOf<String, String>()

                param["title"] = i_title
                param["price"] = i_price
                param["description"] = i_description
                param["image"] = encodeImage ?: ""
                param["table"] = tName

                return param
            }
        }

        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(request)
    }

    private fun imageToString(): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imgByte = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imgByte, Base64.DEFAULT)
    }

    private fun imageStore(bitmap: Bitmap) {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        val imagebyte = stream.toByteArray()
        encodeImage = Base64.encodeToString(imagebyte, Base64.DEFAULT)
    }
}