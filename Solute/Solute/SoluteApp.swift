//
//  SoluteApp.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import SwiftUI

@main
struct SoluteApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
