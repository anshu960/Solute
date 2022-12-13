//
//  BharatBandhuApp.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI
import Firebase

@main
struct BharatBandhuApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    let persistenceController = PersistenceController.shared
    @Environment(\.scenePhase) var scenePhase
    
    var body: some Scene {
        
        WindowGroup {
            
            OnBaordingNavigation()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
        .onChange(of: scenePhase) { (phase) in
            switch phase {
            case .active:
                SocketService.shared.connect()
//                FirebaseApp.configure()
            case .background:
                print("ScenePhase: Background")
                
            case .inactive:
                print("ScenePhase: inactive entering")
            @unknown default: print("ScenePhase: unexpected state")
            }
        }
    }
}
