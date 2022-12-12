//
//  SubmitButton.swift
//  Solute
//
//  Created by Vivek Singh on 08/12/22.
//

import SwiftUI

struct SubmitButton: View {
    @State var onClick : () -> ()
    @State var title: String

    var body: some View {
        Button {
            onClick()
        } label: {
            Text(title)
        }
        .buttonStyle(.borderedProminent)
        .backgroundStyle(.blue)
        .tint(.blue)
    }
}

struct SubmitButton_Previews: PreviewProvider {
    static var previews: some View {
        SubmitButton( onClick: {}, title: "")
    }
}
