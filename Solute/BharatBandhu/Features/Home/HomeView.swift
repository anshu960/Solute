//
//  HomeView.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI

struct HomeView: View {
    init() {
            UITabBar.appearance().barTintColor = UIColor.blue
        }

    var body: some View {
        TabView {
            MembershipView()
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("Membership")
                }
            
            ProductsView()
                .tabItem {
                    Image(systemName: "menucard.fill")
                    Text("Products")
                }
        }.onAppear() {
            UITabBar.appearance().backgroundColor = ThemeColor.defaultThemeColor
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}

