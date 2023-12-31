//
//  RegisterView.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI

struct RegisterView: View {
    @State var name = ""
    var body: some View {
        TextField("Display Name", text: $name)
            .keyboardType(.namePhonePad)
            .textContentType(.name)
            .padding()
            .frame(width:300,height: 54)
            .cornerRadius(14)
            .border(.gray,width: 1)
        Spacer(minLength: 12)
        SubmitButton(onClick: {
            AuthenticationViewModel.shared.registerUser(name: name)
        },title: "Continue")
        Spacer()
    }
}

struct RegisterView_Previews: PreviewProvider {
    static var previews: some View {
        RegisterView()
    }
}
