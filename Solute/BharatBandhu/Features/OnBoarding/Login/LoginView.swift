//
//  LoginView.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI

struct LoginView: View {
    @State var mobile: String = AuthenticationViewModel.shared.mobileNumber
    
    @State var verificationId = AuthenticationViewModel.shared.verificationId
    @State var error = ""
    let phoneViewModal = PhoneViewModel()
    
    var body: some View {
        VStack(spacing: 0) {
            Image("launch_image").resizable()
                .frame(width: 200, height: 200)
            InputFieldMobile(mobile: $mobile)
            
            SubmitButton(onClick: {
                AuthenticationViewModel.shared.sendOtp(mobile: mobile)
            },title: "Send OTP")
            Spacer()
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
