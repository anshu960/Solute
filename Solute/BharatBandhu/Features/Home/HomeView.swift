//
//  HomeView.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI

struct HomeView: View {
    @State var barcode = CustomerViewModel.shared.barcode
    var body: some View {
        Group{
            Image("launch_image").resizable()
                .frame(width: 200, height: 200)
                .onAppear(){
                    CustomerViewModel.shared.loadMembership()
                }
            if let barImage = barcode{
                Image(uiImage: barImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                Spacer()
            }
            Spacer()
        }
        
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
