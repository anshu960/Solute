//
//  Preferences.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 18/03/23.
//

import UtilityKit
class Preferences {
    public static let shared = Preferences()
    let defaults = UserDefaults.standard
    
    public func store(value:Any,key:String){
        defaults.setValue(value, forKey: key)
        defaults.synchronize()
    }
    public func remove(key:String){
        defaults.removeObject(forKey: key)
        defaults.synchronize()
    }
    public func dictionary(forKey:String) -> [String:Any] {
        if let json = defaults.value(forKey: forKey) as? String{
            return json.toDictionary()
        }else if let dict = defaults.value(forKey: forKey) as? [String:Any]{
            return dict
        }else{
            return [:]
        }
    }
}
