package com.example.note

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.note.db.IntentConstants
import com.example.note.db.MyAdapter
import com.example.note.db.MyDbManager
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*

class EditActivity : AppCompatActivity() {

    val imageRequestCode = 10
    var tempImageUri = "empty"
    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        getMyIntents()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == imageRequestCode){
            mainImage.setImageURI(data?.data)
            bigImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
            contentResolver.takePersistableUriPermission(data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    fun onClickAddImage(view: View) {
        mainImageLayout.visibility = View.VISIBLE
        Btn_add_image.visibility = View.GONE
    }

    fun onClickImage(view: View){
        if(bigImageLayout.visibility == View.GONE) {
            bigImageLayout.visibility = View.VISIBLE
            mainImageLayout.visibility = View.GONE
        }else {
            bigImageLayout.visibility = View.GONE
            mainImageLayout.visibility = View.VISIBLE
        }
    }

    fun onClickDeleteImage(view: View) {
        mainImageLayout.visibility = View.GONE
        Btn_add_image.visibility = View.VISIBLE
    }

    fun onClickChooseImage(view: View){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, imageRequestCode)
    }

    fun onClickSave(view: View){
        val myTitle = edit_title.text.toString()
        val myDesc = edit_content.text.toString()

        if(myTitle != "" && myDesc != ""){
            if (intent.getIntExtra(BaseColumns._ID, 0) != 0)
                myDbManager.removeItemFromDb(intent.getIntExtra(BaseColumns._ID, 0).toString())
            myDbManager.insertToDb(myTitle,myDesc,tempImageUri)
            finish()
        }
    }

    fun getMyIntents(){
        val i = intent

        if (i != null){

            if(i.getStringExtra(IntentConstants.I_TITLE_KEY) != null){

                edit_title.setText(i.getStringExtra(IntentConstants.I_TITLE_KEY))
                edit_content.setText(i.getStringExtra(IntentConstants.I_CONTENT_KEY))
                if(i.getStringExtra(IntentConstants.I_URI_KEY) != "empty"){

                    mainImageLayout.visibility = View.VISIBLE
                    Btn_add_image.visibility = View.GONE
                    mainImage.setImageURI(Uri.parse(i.getStringExtra(IntentConstants.I_URI_KEY)))
                    bigImage.setImageURI(Uri.parse(i.getStringExtra(IntentConstants.I_URI_KEY)))
                }

            }

        }


    }

}