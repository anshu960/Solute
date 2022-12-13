//
//  Onboarding.swift
//  Solute
//
//  Created by Vivek Singh on 08/12/22.
//
import SwiftUI
import Combine
import FirebaseAuth
import UtilityKit
final class Router: ObservableObject {
    @Published var path = NavigationPath()
}
class AuthenticationViewModel:ObservableObject{
    public static let shared = AuthenticationViewModel()
    
    @Published var isLoggedin = false
    @Published var isFreshLaunch = true
    @Published var isNewUser = false
    @Published var otpSent = false
    public static let router = Router()
    @Published var navigationPath: [Screen] = []
    
    let deviceId = UIDevice.current.identifierForVendor?.uuidString ?? ""
    @Published var verificationId = ""
    @Published var error = ""
    @Published var otp = ""
    var mobileNumber = ""
    var userId = ""
    
    
    func checkAuthStatus(){
        let loginDetails = AuthenticationHandler.shared.fetchUserCredentials()
        if let userId = loginDetails[Key.userId] as? String, userId != ""{
            setHomeScreen()
            return
        }
        if(loginDetails.isEmpty && verificationId == "" && mobileNumber == ""){
            isFreshLaunch = true
            isLoggedin = false
            if(AuthenticationViewModel.shared.navigationPath.count == 0){
                AuthenticationViewModel.shared.navigationPath = []
                setLoginScreen()
            }
        }else if(!mobileNumber.isEmpty && !verificationId.isEmpty){
            setOtpScreen()
        }else if(isNewUser){
            setRegisterScreen()
        }
    }
    
    func setLoginScreen(){
        AuthenticationViewModel.shared.navigationPath = []
        AuthenticationViewModel.shared.navigationPath.append(.screen1)
    }
    
    func setOtpScreen(){
        AuthenticationViewModel.shared.navigationPath = []
        AuthenticationViewModel.shared.navigationPath.append(.screen2)
    }
    func setRegisterScreen(){
        AuthenticationViewModel.shared.navigationPath = []
        AuthenticationViewModel.shared.navigationPath.append(.screen3)
    }
    
    func setHomeScreen(){
        AuthenticationViewModel.shared.navigationPath = []
        AuthenticationViewModel.shared.navigationPath.append(.screen4)
    }
    
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
                self.verificationId = verifyID ?? ""
                self.error = error?.localizedDescription ?? ""
                self.setOtpScreen()
            }
    }
    func verifyOtp(otp:String){
        let credential = PhoneAuthProvider.provider().credential(
            withVerificationID: self.verificationId,
            verificationCode: otp
        )
        Auth.auth().settings?.isAppVerificationDisabledForTesting = true
        Auth.auth().signIn(with: credential) { authResult, error in
            if let error = error {
                let authError = error as NSError
                self.error = authError.localizedDescription
                return
            }
            // User is signed in
            // ...
            if let userID = authResult?.user.uid{
                self.userId = userID
                SocketService.shared.joinRoom(roomId: userID)
                var request : [String:Any] = [:]
                request[Key.mobileNumber] = self.mobileNumber
                request[Key.userId] = userID
                request[Key.deviceId] = self.deviceId
                SocketService.shared.send(eventName: AuthenticationSocketEvent.AUHTENTICATE.rawValue, payload: request)
            }
        }
    }
    func registerUser(name:String){
        var request : [String:Any] = [:]
        request[Key.mobileNumber] = self.mobileNumber
        request[Key.userId] = userId
        request[Key.deviceId] = self.deviceId
        request[Key.name] = name
        SocketService.shared.send(eventName: AuthenticationSocketEvent.AUHTENTICATE.rawValue, payload: request)
    }
    
    
}
