//
//  ExtensionDouble.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import Foundation
extension Double{
    public func formateNumber()->String{
        let formatter = NumberFormatter()
        formatter.numberStyle = NumberFormatter.Style.decimal
        let formatString = formatter.string(from: self as NSNumber) ?? ""
        return formatString
    }
    
    public func rounded(places:Int) ->Double{
        if let rounded = Double(String(format: "%.\(places)f", self)){
            return rounded
        }else{
            return self
        }
    }
    
}
