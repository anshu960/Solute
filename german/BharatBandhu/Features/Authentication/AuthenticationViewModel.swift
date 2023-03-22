//
//  Onboarding.swift
//  Solute
//
//  Created by Vivek Singh on 08/12/22.
//
import FirebaseAuth
import UtilityKit
class AuthenticationViewModel{
    public static let shared = AuthenticationViewModel()
    let businessId = "6364c00fb25244fcf46425f0"
    
    var isLoggedin = false
    var isFreshLaunch = true
    var isNewUser = false
    var otpSent = false
    
    public static let deviceId = UIDevice.current.identifierForVendor?.uuidString ?? ""
    var verificationId = ""
    var error = ""
    var otp = ""
    var mobileNumber = ""
    var userId = ""
    var isLoading = false
    
    var onOtpSent : ((_ msg:String)->Void?)? = nil
    var onOtpVerify : ((_ msg:String)->Void?)? = nil
    
    func sendOtp(mobile:String){
        mobileNumber = mobile.replacingOccurrences(of: "-", with: "")
        mobileNumber = mobileNumber.replacingOccurrences(of: " ", with: "")
        mobileNumber = mobileNumber.replacingOccurrences(of: "(", with: "")
        mobileNumber = mobileNumber.replacingOccurrences(of: ")", with: "")
        if(mobileNumber.contains("945506")){
            mobileNumber = "+919455068676"
        }else{
            mobileNumber = "+49\(mobileNumber)"
        }
        PhoneAuthProvider.provider()
            .verifyPhoneNumber(mobileNumber, uiDelegate: nil) { verifyID, error in
                if let err = error{
                    self.onOtpSent?(err.localizedDescription)
                }else{
                    self.onOtpSent?("sent")
                }
                self.verificationId = verifyID ?? ""
                self.error = error?.localizedDescription ?? ""
            }
    }
    func verifyOtp(otp:String){
        let credential = PhoneAuthProvider.provider().credential(
            withVerificationID: self.verificationId,
            verificationCode: otp
        )
        isLoading = true
        Auth.auth().settings?.isAppVerificationDisabledForTesting = true
        Auth.auth().signIn(with: credential) { authResult, error in
            if let error = error {
                let authError = error as NSError
                self.error = authError.localizedDescription
                self.isLoading = false
                self.onOtpVerify?(error.localizedDescription)
                return
            }
            // User is signed in
            // ...
            if let userID = authResult?.user.uid{
                self.userId = userID
                var request : [String:Any] = [:]
                request[Key.mobileNumber] = self.mobileNumber
                request[Key.userId] = userID
//                self.authenticateUserWithServer(payload: request)
                self.onOtpVerify?("")
                SocketService.shared.send(eventName: AuthenticationSocketEvent.AUHTENTICATE.rawValue, payload: request)
            }
        }
    }
    func registerUser(name:String){
        var request : [String:Any] = [:]
        request[Key.mobileNumber] = self.mobileNumber
        request[Key.userId] = userId
        request[Key.name] = name
        SocketService.shared.send(eventName: AuthenticationSocketEvent.AUHTENTICATE.rawValue, payload: request)
    }
    
    func authenticateUserWithServer(payload:[String:Any]){
        var request = payload
        request[Key.deviceId] = AuthenticationViewModel.deviceId
        let api = Server.shared.servers.first! + "/api/" + SocketEvent.AUHTENTICATE.rawValue
        ServiceManager.makeServiceCall(toApi: api , requestData: request) { response,result in
            DispatchQueue.main.async {
                AuthenticationViewModel.shared.isLoading = false
                if let payload = response[Key.payload] as? [String:Any],let id = payload[Key._id] as? String,!id.isEmpty{
//                    AuthenticationHandler.shared.storeUserCredentials(userData: payload)
                }else{
//                    AuthenticationViewModel.shared.setRegisterScreen()
                }
            }
        }
    }
}
