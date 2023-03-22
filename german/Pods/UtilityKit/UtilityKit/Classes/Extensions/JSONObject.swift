//
//  JSONObject.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import Foundation
public class JSONObject: NSMutableDictionary {
    
    public static var finalObject : NSMutableDictionary? = nil
    public static let shared = JSONObject()
    
//    @objc override func put(_ key:String,_ val:Any) {
//        self[key] = val
//    }
}
