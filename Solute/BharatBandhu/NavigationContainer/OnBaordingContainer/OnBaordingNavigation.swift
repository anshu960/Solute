//
//  OnBaordingNavigation.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI
import Combine
enum Screen: Hashable {
    case screen1
    case screen2
    case screen3
    case screen4
}

struct OnBaordingNavigation: View {
    @ObservedObject var viewModel: AuthenticationViewModel = AuthenticationViewModel.shared
    @Environment(\.managedObjectContext) private var viewContext
    
    var body: some View {
        NavigationStack(path: $viewModel.navigationPath) {
            VStack() {
                LaunchView()
            }
            .navigationDestination(for: Screen.self) {screen in
                switch screen {
                case .screen1:
                    LoginView()
                case .screen2:
                    OTPView()
                case .screen3:
                    RegisterView()
                case .screen4:
                    HomeView()
                }
            }
        }.onAppear(){
            viewModel.checkAuthStatus()
        }
    }
}

struct OnBaordingNavigation_Previews: PreviewProvider {
    static var previews: some View {
        OnBaordingNavigation()
    }
}
