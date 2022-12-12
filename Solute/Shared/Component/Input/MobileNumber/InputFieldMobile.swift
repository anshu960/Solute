//
//  InputFieldMobile.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI

struct InputFieldMobile: View {
        @Binding var mobile: String
        @State var isEditing: Bool = false
    
    var body: some View {
        iPhoneNumberField("(000) 000-0000", text: $mobile, isEditing: $isEditing)
            .flagHidden(false)
            .flagSelectable(true)
            .font(UIFont(size: 30, weight: .light, design: .monospaced))
            .maximumDigits(10)
            .foregroundColor(Color.pink)
//            .clearButtonMode(.whileEditing)
            .onClear { _ in isEditing.toggle() }
            .accentColor(Color.orange)
            .padding()
            .background(Color.white)
            .cornerRadius(10)
            .shadow(color: isEditing ? .gray : .white, radius: 10)
            .padding()
    }
}
