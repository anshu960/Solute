//
//  HomeView.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI

struct HomeView: View {
    @ObservedObject var viewModel = CustomerViewModel.shared
    
    var body: some View {
        Image("launch_image").resizable()
            .frame(width: 300, height: 300)
            .onAppear(){
                CustomerViewModel.shared.loadMembership()
            }
        Group{
            if viewModel.membershipId != ""{
                BarCodeView(barcode: viewModel.membershipId)
                                .scaledToFit()
                                .padding()
//                                .border(Color.red)
                Text(viewModel.membershipId)
            }else{
                Text("Membership Details Not Found Please Visit our Store to get your Membership")
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

struct BarCodeView: UIViewRepresentable {
    let barcode: String
    func makeUIView(context: Context) -> UIImageView {
        UIImageView()
    }

    func updateUIView(_ uiView: UIImageView, context: Context) {
        uiView.image = UIImage(barcode: barcode)
    }
}
