//
//  LocalStorage.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 30/08/19.
//

import Foundation
public class LocalStorage{
    public static let shared = LocalStorage()
   public let userDefaults = UserDefaults.standard
    public func store(value:Any,key:String){
        userDefaults.setValue(value, forKey: key)
    }
    public func dictionary(forKey:String) -> [String:Any] {
        let json = userDefaults.string(forKey: forKey) ?? "{}"
        return json.toDictionary()
    }
}
