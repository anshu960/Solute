//
//  OTPView.swift
//  Solute
//
//  Created by Vivek Singh on 12/12/22.
//

import SwiftUI

struct OTPView: View {
    @State var otp: String = AuthenticationViewModel.shared.otp
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
            AuthenticationViewModel.shared.verifyOtp(otp: otp)
        },title: "Verify")
        Spacer()
    }
}

struct OTPView_Previews: PreviewProvider {
    static var previews: some View {
        OTPView()
    }
}
