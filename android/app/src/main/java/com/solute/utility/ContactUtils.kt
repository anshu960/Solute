package com.friendly.utility
import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.annotation.RequiresPermission
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.solute.dataclass.ContactData
import com.solute.dataclass.Profile
import com.solute.dataclass.User

/**
 * @author aminography
 */
@RequiresPermission(Manifest.permission.READ_CONTACTS)
fun Context.isContactExists(
    phoneNumber: String
): Boolean {
    val lookupUri = Uri.withAppendedPath(
        ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
        Uri.encode(phoneNumber)
    )
    val projection = arrayOf(
        ContactsContract.PhoneLookup._ID,
        ContactsContract.PhoneLookup.NUMBER,
        ContactsContract.PhoneLookup.DISPLAY_NAME
    )
    contentResolver.query(lookupUri, projection, null, null, null).use {
        return (it?.moveToFirst() == true)
    }
}

@RequiresPermission(Manifest.permission.READ_CONTACTS)
@JvmOverloads
fun Context.retrieveAllContacts(
    searchPattern: String = "",
    retrieveAvatar: Boolean = true,
    limit: Int = -1,
    offset: Int = -1
):Pair<List<ContactData>,ArrayList<String>> {
    val result: MutableList<ContactData> = mutableListOf()
    var mobileNumbers: ArrayList<String> = ArrayList()
    contentResolver.query(
        ContactsContract.Contacts.CONTENT_URI,
        CONTACT_PROJECTION,
        if (searchPattern.isNotBlank()) "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE '%?%'" else null,
        if (searchPattern.isNotBlank()) arrayOf(searchPattern) else null,
        if (limit > 0 && offset > -1) "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} ASC LIMIT $limit OFFSET $offset"
        else ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC"
    )?.use {
        val user = User()
        if (it.moveToFirst()) {
            do {
                val contactId = it.getLong(it.getColumnIndex(CONTACT_PROJECTION[0]))
                val name = it.getString(it.getColumnIndex(CONTACT_PROJECTION[2])) ?: ""
                val hasPhoneNumber = it.getString(it.getColumnIndex(CONTACT_PROJECTION[3])).toInt()
                val phoneNumber: List<String> = if (hasPhoneNumber > 0) {
                    retrievePhoneNumber(contactId)
                } else mutableListOf()
                val avatar = if (retrieveAvatar) retrieveAvatar(contactId).toString() else ""
                for (mobile in phoneNumber){
                    if(mobile.length > 6){
                        val mobile = formatMobileNumber(mobile)
                        val newContact = ContactData(
                            "",
                            user._id,
                            contactId,
                            name,
                            mobile.first,
                            mobile.second,
                            avatar,
                            Profile(),
                            0,
                            "",
                            "")
                        result.add(newContact)
                        if(mobile.first != "" && mobile.first.length > 6){
                            mobileNumbers.add("${mobile.first}")
                        }
                        if(mobile.second != "" && mobile.second.length > 6){
                            mobileNumbers.add("${mobile.second}")
                        }
                    }
                }
            } while (it.moveToNext())
        }
    }
    return (result to mobileNumbers)
}

private fun Context.retrievePhoneNumber(contactId: Long): List<String> {
    val result: MutableList<String> = mutableListOf()
    contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} =?",
        arrayOf(contactId.toString()),
        null
    )?.use {
        if (it.moveToFirst()) {
            do {
                result.add(it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("\\s".toRegex(), ""))
            } while (it.moveToNext())
        }
    }
    return result
}

fun formatMobileNumber(phone: String) : Pair<String,String>{
    val phoneInstance = PhoneNumberUtil.getInstance()
    try {
        val phoneNumber = phoneInstance.parse(phone, null)
        val countryCode = phoneNumber.countryCode?.toString()?:""
        val mobile = phoneNumber?.nationalNumber?.toString()?:phone
        return (countryCode to  mobile)
    }catch (_ : Exception) {
    }
    return ("" to phone)
}

private fun Context.retrieveAvatar(contactId: Long): Uri? {
    return contentResolver.query(
        ContactsContract.Data.CONTENT_URI,
        null,
        "${ContactsContract.Data.CONTACT_ID} =? AND ${ContactsContract.Data.MIMETYPE} = '${ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE}'",
        arrayOf(contactId.toString()),
        null
    )?.use {
        if (it.moveToFirst()) {
            val contactUri = ContentUris.withAppendedId(
                ContactsContract.Contacts.CONTENT_URI,
                contactId
            )
            Uri.withAppendedPath(
                contactUri,
                ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
            )
        } else null
    }
}

private val CONTACT_PROJECTION = arrayOf(
    ContactsContract.Contacts._ID,
    ContactsContract.Contacts.LOOKUP_KEY,
    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
    ContactsContract.Contacts.HAS_PHONE_NUMBER
)

