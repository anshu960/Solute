//
//  OTPView.swift
//  Solute
//
//  Created by Vivek Singh on 12/12/22.
//

import SwiftUI

struct OTPView: View {
    @State var otp: String = AuthenticationViewModel.shared.otp
    @State var isLoading: Bool = AuthenticationViewModel.shared.isLoading
    var body: some View {
        TextField("OTP", text: $otp)
            .keyboardType(.namePhonePad)
            .textContentType(.oneTimeCode)
            .padding()
            .frame(width:200,height: 54)
            .cornerRadius(14)
            .border(.gray,width: 1)
        
        Spacer(minLength: 12)
        SubmitButton(onClick: {
            isLoading = true
            AuthenticationViewModel.shared.verifyOtp(otp: otp)
        },title: "Verify")
        Group{
            if isLoading{
                ActivityIndicator()
                    .frame(width: 200, height: 200)
                    .foregroundColor(.blue)
            }
        }
        
        
        Spacer()
    }
}

struct OTPView_Previews: PreviewProvider {
    static var previews: some View {
        OTPView()
    }
}
