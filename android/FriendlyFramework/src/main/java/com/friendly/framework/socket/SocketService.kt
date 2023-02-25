package com.friendly.framework.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.provider.ContactsContract
import android.util.Log
import com.friendly.framework.Defaults
import com.friendly.framework.Encryption
import com.friendly.framework.UtilityViewController
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.constants.Server
import com.friendly.framework.dataclass.ContactData
import com.friendly.framework.dataclass.Conversation
import com.friendly.framework.dataclass.Message
import com.friendly.framework.feature.address.network.AddressNetwork
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.businessType.handler.BusinessTypeHandler
import com.friendly.framework.feature.cart.handler.CartHandler
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.employee.handler.EmployeeHandler
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.mediaFile.network.MediaFileNetwork
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.sync.SyncHandler
import com.friendly.framework.socket.repository.SocketRepository
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONArray
import org.json.JSONObject
import java.net.URISyntaxException

class SocketService : Service() {

    val repository = SocketRepository()
    var mSocket: Socket? = null
    var socketServerUrl = Server.allServers[0]
//    var socketServerUrl = ""

    init {
        instance = this
    }

    companion object {
        private var instance: SocketService? = null
        fun shared(): SocketService {
            if (instance != null) {
                return instance as SocketService
            } else {
                return SocketService()
            }
        }
    }

    var salt = ""
    var iv = ""

    val TAG = "SOCKET.IO.MANAGER"
    var conversation: Conversation? = null
    var isSocketConnected = false
    lateinit var currentActivity: UtilityViewController
    var onEvent: (SocketEvent, JSONObject) -> Unit = fun(event: SocketEvent, data: JSONObject) {
    }
    var onEventArray: (SocketEvent, JSONArray) -> Unit = fun(event: SocketEvent, data: JSONArray) {
    }
    var onContacts: (SocketEvent, ArrayList<ContactData>) -> Unit =
        fun(event: SocketEvent, data: ArrayList<ContactData>) {
        }
    var onProfiles: (SocketEvent, ArrayList<ContactsContract.Profile>) -> Unit =
        fun(event: SocketEvent, data: ArrayList<ContactsContract.Profile>) {
        }
    var onConversation: (SocketEvent, Conversation) -> Unit =
        fun(event: SocketEvent, data: Conversation) {
        }
    var onConversations: (SocketEvent, ArrayList<Conversation>) -> Unit =
        fun(event: SocketEvent, data: ArrayList<Conversation>) {
        }

    fun send(event: SocketEvent, data: JSONObject) {
        Log.d(TAG, "Sending Event ${event.value}")
        var requestData = data
        requestData.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        mSocket?.emit(event.value, requestData)
    }
    fun send(event: String, data: JSONObject) {
        Log.d(TAG, "Sending Event $event")
        var requestData = data
        requestData.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        mSocket?.emit(event, requestData)
    }

    fun connect() {
        val opts = IO.Options()
        opts.forceNew = true
        opts.reconnection = false

        mSocket?.close()
        try {
            prepateSocketServerUrl()
            mSocket = IO.socket(socketServerUrl, opts)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
        removeSocketEventListeners()
        mSocket?.on(Socket.EVENT_CONNECT, onConnect)
        mSocket?.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket?.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket?.on(SocketEvent.joinRoom.value, joinRoom)
        mSocket?.on(SocketEvent.getEncryptionKeys.value, encryptionKeyHandler)
        mSocket?.on(SocketEvent.authenticate.value, onSocketEvent)
        mSocket?.on(SocketEvent.findCustomerByMobile.value, onSocketEvent)
        mSocket?.on(SocketEvent.updateFriendlyProfile.value, onSocketEvent)
        mSocket?.on(SocketEvent.getAllMessage.value, retriveMessage)
        mSocket?.on(SocketEvent.onMessage.value, onMessage)
        mSocket?.on(SocketEvent.messageReceived.value, messageReceived)
        mSocket?.on(SocketEvent.messageRead.value, messageRead)
        mSocket?.on(SocketEvent.typing.value, typing)
        mSocket?.on(SocketEvent.previousChats.value, previousChats)
        mSocket?.on(SocketEvent.newChats.value, newChats)
        mSocket?.on(SocketEvent.updateDeliveryStatus.value, updateDeliveryStatus)
        mSocket?.on(SocketEvent.updateReadStatus.value, updateReadStatus)
        mSocket?.on(SocketEvent.findUsers.value, findUsers)
        mSocket?.on(SocketEvent.createConversation.value, createConversation)
        mSocket?.on(SocketEvent.getAllConversation.value, getAllConversation)
        mSocket?.on(SocketEvent.findCompanyByName.value, findCompanyByName)
        mSocket?.on(SocketEvent.findDesignationByName.value, findDesignationByName)
        mSocket?.on(SocketEvent.findTechnologyByName.value, findTechnologyByName)
        mSocket?.on(SocketEvent.createExperience.value, createExperience)
        mSocket?.on(SocketEvent.getAllExperience.value, getAllExperience)
        mSocket?.on(SocketEvent.createGroup.value, createConversation)
        mSocket?.on(SocketEvent.updateGroup.value, eventHandler)
        mSocket?.on(SocketEvent.updateGroupName.value, eventHandler)
        mSocket?.on(SocketEvent.updateGroupDescription.value, eventHandler)
        mSocket?.on(SocketEvent.updateGroupImage.value, eventHandler)
        mSocket?.on(SocketEvent.deleteGroup.value, eventHandler)
        mSocket?.on(SocketEvent.updateContacts.value, updateContacts)
        mSocket?.on(SocketEvent.retriveContacts.value, retriveContacts)
        mSocket?.on(SocketEvent.createTask.value, onSocketEvent)
        mSocket?.on(SocketEvent.updateTask.value, onSocketEvent)
        mSocket?.on(SocketEvent.getAllTask.value, onSocketEventArray)
        mSocket?.on(SocketEvent.attachDocumentTask.value, onSocketEvent)
        mSocket?.on(SocketEvent.onTaskMessage.value, onSocketEvent)
        mSocket?.on(SocketEvent.getAllTaskMessage.value, onSocketEventArray)
        mSocket?.on(SocketEvent.getAllAttachedDocumentTask.value, onSocketEventArray)
        mSocket?.on(SocketEvent.updateTaskStatus.value, onSocketEvent)
        mSocket?.on(SocketEvent.updateTaskPriority.value, onSocketEvent)
        mSocket?.on(SocketEvent.updateTaskPriority.value, onSocketEvent)
        //Solute
        mSocket?.on(SocketEvent.RETRIVE_BUSINESS.value, BusinessHandler.shared().retriveBusiness)
        mSocket?.on(
            SocketEvent.RETRIVE_BUSINESS_TYPE.value,
            BusinessTypeHandler.shared().retriveBusinessType
        )
        mSocket?.on(SocketEvent.CREATE_BUSINESS.value, BusinessHandler.shared().onCreateNewBusiness)
        mSocket?.on(SocketEvent.DELETE_BUSINESS.value,BusinessHandler.shared().onDeleteBusiness)
        mSocket?.on(SocketEvent.RETRIVE_PRODUCT.value, ProductHandler.shared().retriveProduct)
        mSocket?.on(SocketEvent.CREATE_PRODUCT.value, ProductHandler.shared().onCreateProduct)
        mSocket?.on(
            SocketEvent.UPDATE_PRODUCT_IMAGE.value,
            ProductHandler.shared().onUpdateProductImage
        )
        mSocket?.on(SocketEvent.UPDATE_PRODUCT.value, ProductHandler.shared().onUpdateProduct)
        mSocket?.on(SocketEvent.DELETE_PRODUCT.value, ProductHandler.shared().onDeleteProduct)
        mSocket?.on(SocketEvent.CREATE_SALE.value, CartHandler.shared().createSale)
        mSocket?.on(SocketEvent.RETRIVE_SINGLE_INVOICE.value, InvoiceHandler.shared().onRetrieveSingleInvoice)
        mSocket?.on(
            SocketEvent.GENERATE_CUSTOMER_INVOICE.value,
            CartHandler.shared().onCreateCustomerInvoice
        )
        mSocket?.on(SocketEvent.RETRIVE_INVOICE.value, InvoiceHandler.shared().retriveInvoice)
        mSocket?.on(SocketEvent.RETRIVE_SALE.value, SyncHandler.shared().onRetriveSale)
        mSocket?.on(SocketEvent.RETRIVE_SALES.value, InvoiceHandler.shared().retriveSales)
        mSocket?.on(SocketEvent.CREATE_CUSTOMER.value, CustomerHandler.shared().onCreateCustomer)
        mSocket?.on(SocketEvent.UPDATE_CUSTOMER.value, CustomerHandler.shared().onUpdateCustomer)

        mSocket?.on(SocketEvent.RETRIVE_CUSTOMER.value, CustomerHandler.shared().onFetchAllCustomer)
        mSocket?.on(
            SocketEvent.CREATE_PRODUCT_CATEGORY.value,
            ProductCategoryHandler.shared().onCreateProductCategory
        )
        mSocket?.on(
            SocketEvent.RETRIVE_PRODUCT_CATEGORY.value,
            ProductCategoryHandler.shared().retriveProductCategory
        )
        mSocket?.on(
            SocketEvent.CREATE_PRODUCT_SUB_CATEGORY.value,
            ProductSubCategoryHandler.shared().onCreateProductSubCategory
        )
        mSocket?.on(
            SocketEvent.RETRIVE_PRODUCT_SUB_CATEGORY.value,
            ProductSubCategoryHandler.shared().retriveProductSubCategory
        )
        mSocket?.on(
            SocketEvent.RETRIVE_ALL_STOCK_ENTRY.value,
            SyncHandler.shared().onRetriveAllStockEntry
        )
        mSocket?.on(
            SocketEvent.REMOVE_STOCK_QUANTITY.value,
            ProductHandler.shared().onProductStockUpdate
        )
        mSocket?.on(
            SocketEvent.ADD_STOCK_QUANTITY.value,
            ProductHandler.shared().onProductStockUpdate
        )
        mSocket?.on(
            SocketEvent.RESET_STOCK_QUANTITY.value,
            ProductHandler.shared().onProductStockUpdate
        )
        mSocket?.on(SocketEvent.RETRIVE_EMPLOYEE.value, EmployeeHandler.shared().onFetchAllEmployee)
        mSocket?.on(SocketEvent.FIND_USER.value, EmployeeHandler.shared().onFindUser)
        mSocket?.on(SocketEvent.CREATE_EMPLOYEE.value, EmployeeHandler.shared().onCreateEmployee)
        mSocket?.on(
            SocketEvent.ADD_EMPLOYEE_ATTENDACE.value,
            EmployeeHandler.shared().onCreateEmployeeAttendance
        )
        mSocket?.on(SocketEvent.CREATE_PRODUCT_BAR_CODE.value,ProductHandler.shared().onCreateProductBarCode)
        MediaFileNetwork.shared().connectScoket()
        AddressNetwork.shared().connectScoket()
        //conenct the socket
        mSocket?.connect()
    }

    private fun removeSocketEventListeners() {
        mSocket?.off()
        MediaFileNetwork.shared().disconnectSocket()
        mSocket?.disconnect()
        mSocket?.close()
    }

    fun verifyIfConnectedOrNot() {
        if (mSocket?.connected() != true) {
            connect()
        }
    }

    fun prepateSocketServerUrl() {
        socketServerUrl = Server.allServers[1]
        return
//        if (socketServerUrl == "") {
//            socketServerUrl = Server.allServers[0]
//            return
//        }
//        socketServerUrl = Server.allServers[1]
//        socketServerUrl = when (Server.allServers.indexOf(socketServerUrl)) {
//            0 -> Server.allServers[1]
//            1 -> Server.allServers[2]
//            else -> Server.allServers[0]
//        }
    }


    val onConnect = Emitter.Listener {
        repository.socketConnectionStatus.postValue(1)
        isSocketConnected = true
        if (mSocket?.connected() == true) {
            joinRoom(AuthHandler.shared().deviceId)
        }
    }

    fun joinRoom(id: String) {
        var data = JSONObject()
        data.put(KeyConstant.roomId, id)
        mSocket?.emit(SocketEvent.joinRoom.value, data)
    }

    private val onConnectError = Emitter.Listener {
        repository.socketConnectionStatus.postValue(0)
        Log.d(TAG, "Error connecting \n \n")
        Log.d(TAG, it.toString())
        Log.d(TAG, "\n \n")
        isSocketConnected = false
        connect()
    }

    private val onDisconnect = Emitter.Listener {
        repository.socketConnectionStatus.postValue(0)
        Log.d(TAG, "Error connecting \n \n")
        Log.d(TAG, it.toString())
        Log.d(TAG, "\n \n")
        isSocketConnected = false



        connect()
    }

    private val joinRoom = Emitter.Listener {
        Log.d(TAG, " Got Message \n \n ${it.toString()}")
        Log.d(TAG, it.toString())
        Log.d(TAG, "\n \n")
        this.onEvent(SocketEvent.joinRoom, JSONObject())
    }
    private val encryptionKeyHandler = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val data = it.first()
            if (data is JSONObject) {
                messageObject = data
                iv = messageObject.getString(KeyConstant.encryptionIV)
                salt = messageObject.getString(KeyConstant.encryptionSalt)
                Defaults.shared().store(KeyConstant.encryptionIV, iv)
                Defaults.shared().store(KeyConstant.encryptionSalt, salt)
            }
        }
    }
    private val eventHandler = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val data = it.first()
            if (data is JSONObject) {
                messageObject = data
            }
        }
        this.onEvent(SocketEvent.onEvent, messageObject)
    }

    private val onMessage = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject && conversation != null) {
                messageObject = messsageData
                val message = Message(messsageData)
                message.content = Encryption().decryptMessage(message.content, conversation!!)
//                SQLite.shared().insert(TableNames.message, message.data)
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }

    private val onSocketEvent = Emitter.Listener {
        Log.d(TAG, "Event Received ${it}")
        if (it.count() > 0) {
            val anyData = it.first()
            if (anyData is JSONObject) {
                this.onEvent(SocketEvent.onEvent, anyData)
            }
        }
    }

    private val onSocketEventArray = Emitter.Listener {
        if (it.count() > 0) {
            val anyData = it.first()
            if (anyData is JSONArray) {
                this.onEventArray(SocketEvent.onEvent, anyData)
            }
        }
    }

    private val messageReceived = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject) {
                messageObject = messsageData
                val message = Message(messsageData)
//                SQLite.shared().insert(TableNames.message, message.data)
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }

    private val messageRead = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject) {
                messageObject = messsageData
                val message = Message(messsageData)
//                SQLite.shared().insert(TableNames.message, message.data)
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }

    private val typing = Emitter.Listener {
        var typingData = JSONObject()
        if (it.count() > 0) {
            val data = it.first()
            if (data is JSONObject) {
                typingData = data
            }
        }
        this.onEvent(SocketEvent.typing, typingData)
    }

    private val previousChats = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject) {
                messageObject = messsageData
                val message = Message(messsageData)
//                SQLite.shared().insert(TableNames.message, message.data)
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }
    private val retriveMessage = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject) {
                if (messsageData.has(KeyConstant.payload)) {
                    val payload = messsageData.getJSONArray(KeyConstant.payload)
                    for (i in 0 until payload.length()) {
                        val item = payload.getJSONObject(i)
                        val message = Message(item)
//                        SQLite.shared().insert(TableNames.message, message.data)
                    }
                }
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }

    private val newChats = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject) {
                messageObject = messsageData
                val message = Message(messsageData)
//                SQLite.shared().insert(TableNames.message, message.data)
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }

    private val updateDeliveryStatus = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject) {
                messageObject = messsageData
                val message = Message(messsageData)
//                SQLite.shared().insert(TableNames.message, message.data)
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }

    private val updateReadStatus = Emitter.Listener {
        var messageObject = JSONObject()
        if (it.count() > 0) {
            val messsageData = it.first()
            if (messsageData is JSONObject) {
                messageObject = messsageData
                val message = Message(messsageData)
//                SQLite.shared().insert(TableNames.message, message.data)
            }
        }
        this.onEvent(SocketEvent.onMessage, messageObject)
    }

    private val findUsers = Emitter.Listener {
        var allUsers: ArrayList<ContactsContract.Profile> = ArrayList()
        val list = it.first()
        if (list is JSONArray) {
            for (i in 0 until list.length()) {
                val item = list.getJSONObject(i)
//                allUsers.add(ContactsContract.FriendlyProfile(item))
            }
        }
        this.onProfiles(SocketEvent.findUsers, allUsers)
    }

    private val updateContacts = Emitter.Listener {
        var allContacts: ArrayList<ContactData> = ArrayList()
        val data = it.first()

        if (data is JSONObject) {
            val paylaod = data.getJSONObject(KeyConstant.payload)
            val allContactsFromServer = paylaod.getJSONArray(KeyConstant.allContacts)
            for (i in 0 until allContactsFromServer.length()) {
                try {
                    val item = allContactsFromServer.getJSONObject(i)
                    val newContact = ContactData(item)
                    allContacts.add(newContact)
//                    SQLite.shared().insert(TableNames.contacts, newContact.data)
                } catch (e: Exception) {
                    Log.d(TAG, e.localizedMessage)
                }
            }
        }
        this.onContacts(SocketEvent.updateContacts, allContacts)
    }
    private val retriveContacts = Emitter.Listener {
        var allContacts: ArrayList<ContactData> = ArrayList()
        val data = it.first()

        if (data is JSONObject) {
            val paylaod = data.getJSONArray(KeyConstant.payload)
            for (i in 0 until paylaod.length()) {
                val item = paylaod.getJSONObject(i)
//                val newProfile = ContactsContract.FriendlyProfile(item)
            }
//            Database.shared.updateContact(newProfile)
        }
        this.onContacts(SocketEvent.retriveContacts, allContacts)
    }

    private val createConversation = Emitter.Listener {
        var conversation = Conversation()
        if (it.count() > 0) {
            val messsageDataAny = it.first()
            if (messsageDataAny is JSONObject) {
                conversation = Conversation(messsageDataAny)
//                SQLite.shared().insert(TableNames.conversation, conversation.data)
            }
        }
        this.onConversation(SocketEvent.createConversation, conversation)
    }

    private val getAllConversation = Emitter.Listener {
        var allConversation: ArrayList<Conversation> = ArrayList()
        val list = it.first()
        if (list is JSONArray) {
            for (i in 0 until list.length()) {
                val item = list.getJSONObject(i)
                val conversation = Conversation(item)
                allConversation.add(conversation)
//                SQLite.shared().insert(TableNames.conversation, conversation.data)
            }
        }
        this.onConversations(SocketEvent.getAllConversation, allConversation)
    }

    private val findCompanyByName = Emitter.Listener {
        val data = it.first()
        if (data is JSONArray) {
            this.onEventArray(SocketEvent.findCompanyByName, data)
        }
    }
    private val findDesignationByName = Emitter.Listener {
        val data = it.first()
        if (data is JSONArray) {
            this.onEventArray(SocketEvent.findDesignationByName, data)
        }
    }
    private val findTechnologyByName = Emitter.Listener {
        val data = it.first()
        if (data is JSONArray) {
            this.onEventArray(SocketEvent.findTechnologyByName, data)
        }
    }

    private val createExperience = Emitter.Listener {
        val data = it.first()
        if (data is JSONObject) {
            this.onEvent(SocketEvent.createExperience, data)
        }
    }
    private val getAllExperience = Emitter.Listener {
        val data = it.first()
        if (data is JSONArray) {
            this.onEventArray(SocketEvent.getAllExperience, data)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}
