//
//  MembershipView.swift
//  Solute
//
//  Created by Vivek Singh on 05/01/23.
//

import SwiftUI

struct MembershipView: View {
    @ObservedObject var viewModel = CustomerViewModel.shared
    
    var body: some View {
        VStack{
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
                        .onAppear(){
                            CustomerViewModel.shared.loadMembership()
                        }
                }
                Spacer()
                Text("Please show this barcode at our Stores while purchasing")
            }
        }
    }
}

struct MembershipView_Previews: PreviewProvider {
    static var previews: some View {
        MembershipView()
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
