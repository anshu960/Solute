//
//  PinSttackView.swift
//  Solute
//
//  Created by Vivek Singh on 08/12/22.
//

import SwiftUI
public struct OtpTextFieldView: View {
    enum FocusField: Hashable {
        case field
    }
    @ObservedObject var phoneViewModel: PhoneViewModel
    @FocusState private var focusedField: FocusField?
    
    init(phoneViewModel: PhoneViewModel){
        self.phoneViewModel = phoneViewModel
    }
    
    private var backgroundTextField: some View {
        return TextField("OTP", text: $phoneViewModel.pin)
            .frame(width: 0, height: 0, alignment: .center)
            .font(Font.system(size: 0))
            .accentColor(.blue)
            .foregroundColor(.blue)
            .multilineTextAlignment(.center)
            .keyboardType(.numberPad)
//            .onReceive(phoneViewModel.pin) { _ in phoneViewModel.limitText(60) }
            .focused($focusedField, equals: .field)
            .task {
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.5)
                {
                    self.focusedField = .field
                }
            }
            .padding()
    }
    
    public var body: some View {
        ZStack(alignment: .center) {
            backgroundTextField
            HStack {
                ForEach(0..<60) { index in
                    ZStack {
                        Text(phoneViewModel.getPin(at: index))
                            .font(Font.system(size: 27))
                            .fontWeight(.semibold)
                            .foregroundColor(Color.gray)
                        Rectangle()
                            .frame(height: 2)
                            .foregroundColor(Color.gray)
                            .padding(.trailing, 5)
                            .padding(.leading, 5)
                            .opacity(phoneViewModel.pin.count <= index ? 1 : 0)
                    }
                }
            }
        }
    }
}
