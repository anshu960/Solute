package com.utilitykit.database


import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.utility.Encryption
import com.utilitykit.Constants.TableNames
import com.utilitykit.Constants.TableScript
import com.utilitykit.UtilityKitApp
import com.utilitykit.dataclass.*
import com.utilitykit.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Database {

    init {
        instance = this
    }

    companion object{
        private var instance: Database? = null
        fun shared() : Database {
            if(instance != null){
                return instance as Database
            }else{
                return Database()
            }
        }
    }

    fun setUp(context: Context) {
        shared().createTables()
    }

    fun createTables(){
//        SQLite.execute( TableScript.createMessageTable)
//        SQLite.execute(TableScript.createConversationTable)
//        SQLite.execute(TableScript.createProfilesTable)
//        SQLite.execute(TableScript.createFriendsTable)
//        SQLite.execute(TableScript.createfriendRequestTable)
//        SQLite.execute(TableScript.createBlockListTable)
//        SQLite.execute(TableScript.createFilesTable)
          SQLite.shared().execute(TableScript.createSaleTable)
    }

    suspend fun retriveConversations(query:String,callBack:(ArrayList<Conversation>)->Unit) = withContext(
        Dispatchers.Default) {
        var conversations:ArrayList<Conversation> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.conversation} WHERE FromUserID = '${UtilityKitApp.user._id}' OR ToUserID = '${UtilityKitApp.user._id}' ORDER BY UpdatedAt IS NULL OR UpdatedAt=''"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            val newConversation = Conversation(item)
            newConversation.message = retriveMessage(newConversation._id)
//            newConversation.message.content =  Encyption().decrypt(newConversation.message.content,newConversation)
            conversations.add(newConversation)
        }
        Log.d("Number","Of Conversations  = ${conversations.count()}")
        callBack.invoke(conversations)
    }

    suspend fun searchConversations(query:String,callBack:(ArrayList<Conversation>)->Unit) = withContext(Dispatchers.Default) {
        var newQuery = query//.toLowerCase()
        var conversations:ArrayList<Conversation> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.conversation} WHERE (ParticipantProfiles LIKE '%$newQuery%' COLLATE NOCASE) AND (FromUserID = '${UtilityKitApp.user._id}' OR ToUserID = '${UtilityKitApp.user._id}') ORDER BY UpdatedAt IS NULL OR UpdatedAt=''"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            val newConversation = Conversation(item)
            newConversation.message = Database.shared().retriveMessage(newConversation._id)
            newConversation.message.content =  Encryption().decrypt(newConversation.message.content,newConversation)
            conversations.add(newConversation)
        }
        Log.d("Number","Of Conversations  = ${conversations.count()}")
        callBack.invoke(conversations)
    }
    fun updateGroupName(name:String,_id:String){
        val updateQuery = "UPDATE ${TableNames.conversation}  SET GroupName='${name}' WHERE _id='${_id}'"
        SQLite.shared().execute(updateQuery)
    }
    fun updateGroupDescription(description:String,_id:String){
        val updateQuery = "UPDATE ${TableNames.conversation}  SET GroupDescription='${description}' WHERE _id='${_id}'"
        SQLite.shared().execute(updateQuery)
    }
    fun updateGroupProfilePicture(picture:String,_id:String){
        val updateQuery = "UPDATE ${TableNames.conversation}  SET ProfilePicture='${picture}' WHERE _id='${_id}'"
        SQLite.shared().execute(updateQuery)
    }

    fun retriveContacts():List<ContactData>{
        var contacts:MutableList<ContactData> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.contacts} "
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            contacts.add(ContactData(item))
        }
        Log.d("Number","Of Conversations  = ${contacts.count()}")
        return contacts
    }

    fun retriveKnownContacts():ArrayList<ContactData>{
        var contacts:ArrayList<ContactData> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.contacts} WHERE Profile LIKE '%{%'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            contacts.add(ContactData(item))
        }
        Log.d("Number","Of Conversations  = ${contacts.count()}")
        return contacts
    }

    fun retriveContact(mobile:String):ContactData?{
        val selectQuery = "SELECT  * FROM ${TableNames.contacts} WHERE MobileNumber='${mobile}'"
        val rows = SQLite.shared().getRow(selectQuery)
        if(rows != null && rows.getAsString("MobileNumber") != ""){
            return ContactData(rows)
        }else{
            print("Contact is not null")
        }
        return null
    }
    fun updateContact(user:Profile){
        val updateQuery = "UPDATE ${TableNames.contacts}  SET _id='${user._id}',ProfilePicture='${user.profilePic}',Profile='${user.data.json()}' WHERE MobileNumber='${user.mobileNumber}'"
        SQLite.shared().execute(updateQuery)
    }


    fun searchContact(query:String):List<ContactData>{
        val newQuery = query.toLowerCase()
        var contacts:MutableList<ContactData> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.contacts} WHERE MobileNumber LIKE '%$newQuery%' OR Name LIKE '%$newQuery%'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            contacts.add(ContactData(item))
        }
        Log.d("Number","Of Conversations  = ${contacts.count()}")
        return contacts
    }

    fun retriveConversation(fromId:String,toid:String):Conversation?{
        val selectQuery = "SELECT  * FROM ${TableNames.conversation} WHERE (FromUserID = '${fromId}' AND ToUserID = '${toid}') OR (FromUserID = '${toid}' AND ToUserID = '${fromId}') ORDER BY UpdatedAt IS NULL OR UpdatedAt=''"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        if(rows.count() > 0){
            return  Conversation(rows.first())
        }
        return null
    }

    fun retriveConversationByID(conversationId:String):Conversation?{
        val selectQuery = "SELECT  * FROM ${TableNames.conversation} WHERE _id = '${conversationId}'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        if(rows.count() > 0){
            return  Conversation(rows.first())
        }
        return null
    }


    fun deleteConversationByID(conversationId:String):Conversation?{
        val selectQuery = "DELETE FROM  ${TableNames.conversation} WHERE _id = '${conversationId}'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        if(rows.count() > 0){
            return  Conversation(rows.first())
        }
        return null
    }

    suspend fun retriveMessages(conversation:Conversation):ArrayList<Message> = withContext(Dispatchers.Default) {
        var messages:ArrayList<Message> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.message} WHERE ConversationId = '${conversation._id}' ORDER BY SentTime IS NULL OR SentTime=''"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            val message = Message(item)
//            message.content = Encyption().decrypt(message.content,conversation)
            messages.add(message)
        }
        Log.d("Number","Of Messages  = ${messages.count()}")
        return@withContext messages
    }

    suspend fun retriveMessage(conversationId:String):Message = withContext(Dispatchers.Default) {
        val selectQuery = "SELECT  * FROM ${TableNames.message} WHERE ConversationId = '$conversationId' ORDER BY SentTime IS NULL OR SentTime=''"
        val row = SQLite.shared().getRowsByQuery(selectQuery)
        if(row.count() > 0){
            return@withContext Message(row.last())
        }else{
            return@withContext Message()
        }
    }

    fun retriveFriends():ArrayList<Profile>{
        var friendList:ArrayList<Profile> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.friends}"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            friendList.add(Profile(item))
        }
        Log.d("Number","Of Friends  = ${friendList.count()}")
        return friendList
    }
    fun retriveProfile(id:String): Profile {
        val selectQuery = "SELECT  * FROM ${TableNames.profile} WHERE UserID='$id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        return Profile(rows)
    }

    fun retriveSentRequest(_id:String):ArrayList<FriendRequest>{
        var friendRequests:ArrayList<FriendRequest> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.friendRequest} WHERE FromUserID='$_id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            friendRequests.add(FriendRequest(item))
        }
        Log.d("Number","Of Friends  = ${friendRequests.count()}")
        return friendRequests
    }

    fun retriveSentRequestCount(_id:String):Int{
        val selectQuery = "SELECT  * FROM ${TableNames.friendRequest} WHERE FromUserID='$_id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        return rows.count()
    }

    fun retriveReceivedRequestCount(_id:String):Int{
        val selectQuery = "SELECT  * FROM ${TableNames.friendRequest} WHERE ToUserID='$_id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        return rows.count()
    }

    fun retriveFriendRequestById(_id:String):FriendRequest?{
        val selectQuery = "SELECT  * FROM ${TableNames.friendRequest} WHERE _id='$_id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        if(rows.count() > 0){
            return FriendRequest(rows.first())
        }else
        {
            return null
        }
    }

    fun retriveReceivedRequest(_id:String):ArrayList<FriendRequest>{
        var friendRequests:ArrayList<FriendRequest> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.friendRequest} WHERE ToUserID='$_id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            friendRequests.add(FriendRequest(item))
        }
        Log.d("Number","Of Friends  = ${friendRequests.count()}")
        return friendRequests
    }

    fun getProfileFromBlockList(id:String): Profile {
        val selectQuery = "SELECT  * FROM ${TableNames.blockList} WHERE UserID='$id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        return Profile(rows)
    }

    fun getfriendRequest(id:String):FriendRequest?{
        val selectQuery = "SELECT  * FROM ${TableNames.friendRequest} WHERE FromUserID='$id' OR ToUserID='$id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        if(rows.count() > 0){
            return FriendRequest(rows.first())
        }else
        {
            return null
        }
    }

    fun getProfileFromReceivedRequest(id:String):Profile{
        val selectQuery = "SELECT  * FROM ${TableNames.friendRequest} WHERE UserID='$id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        return Profile(rows)
    }

    fun getProfileFromFriendList(id:String):Profile{
        val selectQuery = "SELECT  * FROM ${TableNames.friends} WHERE UserID='$id'"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        return Profile(rows)
    }

    fun retriveFileUrl(url:String):String{
        var localUrl = ""
        val selectQuery = "SELECT  * FROM ${TableNames.files} WHERE RemoteURL='${url}'"
        val row = SQLite.shared().getRow(selectQuery)
        if(row.containsKey("LocalURL")){
            localUrl = row.getAsString("LocalURL")
        }
        return localUrl
    }


    fun storeFileUrl(localUrl:String,remoteUrl:String){
        var contentValues = ContentValues()
        contentValues.put("LocalURL",localUrl)
        contentValues.put("RemoteURL",remoteUrl)
        SQLite.shared().insert(TableNames.files,contentValues)
    }

    suspend fun retriveTask(id: String):ArrayList<Task> = withContext(Dispatchers.IO) {
        var allTask:ArrayList<Task> = ArrayList()
//        val selectQuery = "SELECT  * FROM ${TableNames.task} WHERE (Participants LIKE '%$id%' COLLATE NOCASE) ORDER BY UpdatedAt IS NULL OR UpdatedAt=''"
        val selectQuery = "SELECT  * FROM ${TableNames.task} ORDER BY UpdatedAt IS NULL OR UpdatedAt=''"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            val newTask = Task(item)
            allTask.add(newTask)
        }
        Log.d("Number","Of Task  = ${allTask.count()}")
        return@withContext allTask
    }

    suspend fun retriveTaskMessages(_id: String):ArrayList<TaskMessage> = withContext(Dispatchers.IO) {
        var taskMessages:ArrayList<TaskMessage> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.taskMessage} WHERE TaskID = '${_id}' ORDER BY SentTime IS NULL OR SentTime=''"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            val message = TaskMessage(item)
            taskMessages.add(message)
        }
        Log.d("Number","Of taskMessages  = ${taskMessages.count()}")
        return@withContext taskMessages
    }

    suspend fun retriveTaskAttachemnts(_id: String):ArrayList<TaskAttachment> = withContext(Dispatchers.IO) {
        var taskAttachment:ArrayList<TaskAttachment> = ArrayList()
        val selectQuery = "SELECT  * FROM ${TableNames.taskAttachment} WHERE TaskID = '${_id}' ORDER BY UpdatedAt IS NULL OR UpdatedAt=''"
        val rows = SQLite.shared().getRowsByQuery(selectQuery)
        for(item in rows){
            val message = TaskAttachment(item)
            taskAttachment.add(message)
        }
        Log.d("Number","Of taskMessages  = ${taskAttachment.count()}")
        return@withContext taskAttachment
    }
}