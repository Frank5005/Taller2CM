package com.example.taller2

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import androidx.core.content.ContextCompat

class ContactsActivity : AppCompatActivity() {

    var mProjection: Array<String>? = null
    var mCursor: Cursor? = null
    var mContactsAdapter: ContactsAdapter? = null
    var mLista: ListView? = null

    fun initView(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            mCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, mProjection, null, null, null)
            mContactsAdapter?.changeCursor(mCursor)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        mLista = findViewById(R.id.list)

        val mProjection = arrayOf(ContactsContract.Profile._ID, ContactsContract.Profile.DISPLAY_NAME_PRIMARY)
        //val mCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        mContactsAdapter = ContactsAdapter(this, null, 0)
        mLista?.adapter = mContactsAdapter
        initView()
    }

}