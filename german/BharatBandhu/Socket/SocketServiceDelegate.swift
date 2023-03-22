//
//  SocketServiceDelegate.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import Foundation
protocol SocketServiceDelegate {
    func didConnect()
    func didDisconnect()
}
