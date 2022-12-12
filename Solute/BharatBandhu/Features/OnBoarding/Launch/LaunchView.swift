//
//  LaunchView.swift
//  Solute
//
//  Created by Vivek Singh on 12/12/22.
//

import SwiftUI

struct LaunchView: View {
    var body: some View {
        Image("launch_image").resizable()
            .frame(width: 200, height: 200)
    }
}

struct LaunchView_Previews: PreviewProvider {
    static var previews: some View {
        LaunchView()
    }
}
