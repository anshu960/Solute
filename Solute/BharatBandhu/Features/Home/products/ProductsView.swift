//
//  ProductsView.swift
//  Solute
//
//  Created by Vivek Singh on 05/01/23.
//

import SwiftUI

struct ProductsView: View {
    @ObservedObject var viewModel: ProductViewModel = ProductViewModel.shared
    @State var products = ProductViewModel.shared.products
    
    var body: some View {
        VStack{
            Group{
                if viewModel.isLoading{
                    ActivityIndicator()
                        .frame(width: 200, height: 200)
                        .foregroundColor(.blue)
                    Text("Trying to load products for you")
                }else if(products.isEmpty){
                    Text("Couldn't find any stores near you, please try after some time, We might not have presence in your city and country Yet for grocery stores.")
                }else{
                    List{
                        ForEach(viewModel.products) {product in
                            Text(product.name ?? "")
                            Text(product.name ?? "")
                            Text(product.name ?? "")
                        }
                    }
                }
            }
        }.onAppear{ProductViewModel.shared.loadProducts()}
    }
}

struct ProductsView_Previews: PreviewProvider {
    static var previews: some View {
        ProductsView()
    }
}
