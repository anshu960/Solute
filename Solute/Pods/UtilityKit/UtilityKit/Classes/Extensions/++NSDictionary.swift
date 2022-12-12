//
//  extensionNSDictionary.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import Foundation
//Extending NSDictionary To Convert to JSON
extension NSDictionary{
    ///Convert json format of type string
    ///Return Type : String
    public func toJSONStr() -> String {
        if let theJSONData = try? JSONSerialization.data(
            withJSONObject: self,
            options: []) {
            let theJSONText = String(data: theJSONData,
                                     encoding: .utf8)
            return theJSONText!
        }else{
            return ""
        }
    }
    
    ///Get dictionary in json format of type Data
    public func toJSON()-> Data {
        if let theJSONData = try? JSONSerialization.data(
            withJSONObject: self,
            options: []) {
            return theJSONData
        }else{
            return "".data(using: .utf8)!
        }
    }
    ///get String for key
    ///If string string doesn't exist for that key then will return "".
    public func getString(key:String) -> String {
        if let value = self.value(forKey: key) as? String {
            return value
        }else{
            return ""
        }
    }
    
    //MARK:To check if key is there or not
    public func has(_ key:String) -> Bool {
        let allKeys = self.allKeys as! [String]
        if(allKeys.contains(key)){
            return true
        }else{
            return false
        }
    }
    ///Get value for key in dictionary
    ///If value is not there then will return empty dictionary
    public func getDictionary(key:String) -> NSDictionary {
        let retDict : NSDictionary = [:]
        if let dict = self.value(forKey: key) as? NSDictionary{
            return dict
        }else{
            return retDict
        }
    }
    public func getMutableDictionary(key:String) -> NSMutableDictionary {
        let retDict : NSMutableDictionary = [:]
        if let dict = self.value(forKey: key) as? NSDictionary{
            return NSMutableDictionary(dictionary: dict)
        }else{
            return retDict
        }
    }
    
    public func getArray(key:String) -> NSArray {
        return self.value(forKey: key) as? NSArray ?? []
    }
    
    public func getOptionalArray(key:String) -> NSArray? {
        if let arr = self.value(forKey: key) as? NSArray{
            return arr
        }else{
            return nil
        }
    }
    public func getDictArray(key:String) -> [NSDictionary] {
        if let arr = self.value(forKey: key) as? [NSDictionary]{
            return arr
        }else{
            return []
        }
    }
    
    public func getInt(key:String) -> Int64 {
        if let strVal = self.value(forKey: key) as? String{
            return Int64(strVal.replacingOccurrences(of: " ", with: ""))!
        }else{
            return self.value(forKey: key) as? Int64 ?? 0
        }
    }
    public func getOptionalInt(key:String) -> Int64? {
        if let val = self.value(forKey: key) as? Int64{
            return val
        }
        if let val = self.value(forKey: key) as? String{
            if let intVal = Int64(val) {
                return intVal
            }else{
                return nil
            }
        }
        else{
            return nil
        }
    }
    public func getDouble(key:String) -> Double {
        if let strVal = self.value(forKey: key) as? String{
            return Double(strVal) ?? 0
        }else{
            return self.value(forKey: key) as! Double
        }
    }
    
    public func getOptionalDouble(key:String) -> Double? {
        if let strVal = self.value(forKey: key) as? String{
            return Double(strVal)
        }else{
            return self.value(forKey: key) as? Double
        }
    }
    
    public func getBool(key:String)->Bool{
        if let boolStr = self.value(forKey: key) as? String{
            return Bool(boolStr)!
        }else{
            return self.value(forKey: key) as! Bool
        }
    }
    public func getOptionalBool(key:String)->Bool?{
        return self.value(forKey: key) as? Bool
    }
    public func getOptionalString(key:String) -> String? {
        return self.value(forKey: key) as? String
    }
    
    public func optString(key:String) -> String? {
        return self.value(forKey: key) as? String
    }
    
    public func getOptionalDictionary(key:String) -> NSDictionary? {
        return self.value(forKey: key) as? NSDictionary
    }
    
    public func getLong(_ key:String) -> Int64 {
        if let strVal = self.value(forKey: key) as? String{
            return Int64(strVal)!
        }else{
            if let value = self.value(forKey: key) as? Int64{
                return value
            }else{
                return Int64(self.value(forKey: key) as? Int64 ?? 0)
            }
        }
    }
    
    public func optLong(key:String) -> Int64? {
        if let val = self.value(forKey: key) as? Int64{
            return val
        }else{
            return 0
        }
    }
    
    
    //Extending for put method to add key value pair
    public func put(_ col:String,_ val:Any) {
        self.setValue(val, forKey: col)
    }
    
    
}

//Extending Dictionary for keyPath
extension Dictionary {
    subscript(keyPath keyPath: String) -> Any? {
        get {
            guard let keyPath = Dictionary.keyPathKeys(forKeyPath: keyPath)
                else { return nil }
            return getValue(forKeyPath: keyPath)
        }
        set {
            guard let keyPath = Dictionary.keyPathKeys(forKeyPath: keyPath),
                let newValue = newValue else { return }
            self.setValue(newValue, forKeyPath: keyPath)
        }
    }
    
    static public func keyPathKeys(forKeyPath: String) -> [Key]? {
        let keys = forKeyPath.components(separatedBy: ".")
            .reversed().compactMap({ $0 as? Key })
        return keys.isEmpty ? nil : keys
    }
    
    // recursively (attempt to) access queried subdictionaries
    // (keyPath will never be empty here; the explicit unwrapping is safe)
    public func getValue(forKeyPath keyPath: [Key]) -> Any? {
        guard let value = self[keyPath.last!] else { return nil }
        return keyPath.count == 1 ? value : (value as? [Key: Any])
            .flatMap { $0.getValue(forKeyPath: Array(keyPath.dropLast())) }
    }
    
    // recursively (attempt to) access the queried subdictionaries to
    // finally replace the "inner value", given that the key path is valid
    mutating public func setValue(_ value: Any, forKeyPath keyPath: [Key]) {
        guard self[keyPath.last!] != nil else { return }
        if keyPath.count == 1 {
            (value as? Value).map { self[keyPath.last!] = $0 }
        }
        else if var subDict = self[keyPath.last!] as? [Key: Value] {
            subDict.setValue(value, forKeyPath: Array(keyPath.dropLast()))
            (subDict as? Value).map { self[keyPath.last!] = $0 }
        }
    }
}

//Extending For keypath Of type KeyPath
extension NSDictionary{
    //    public func getValue(atPath path:KeyPath) -> Any? {
    //        if(path.key.first == "."){
    //            return self.value(forKeyPath: String(path.key.dropFirst()))
    //        }else{
    //            return self.value(forKeyPath: path.key)
    //
    //        }
    //    }
    //
    //    public func setValue(value:Any, atPath path:KeyPath){
    //        if(path.key.first == "."){
    //            self.setValue(value, forKeyPath: String(path.key.dropFirst()))
    //        }else{
    //            self.setValue(value, forKeyPath: path.key)
    //        }
    //    }
    public func json() -> String {
        if let theJSONData = try? JSONSerialization.data(
            withJSONObject: self,
            options: []) {
            let theJSONText = String(data: theJSONData,
                                     encoding: .utf8)
            return theJSONText!
        }else{
            return ""
        }
    }
    
    public func string(forKey key:String) -> String? {
        return self.value(forKey: key) as? String
    }
    func int(forKey key:String) -> Int64? {
        return self.value(forKey: key) as? Int64
    }
}

extension Dictionary where Key == String{
    public func toJSONStr() -> String {
        if let theJSONData = try? JSONSerialization.data(
            withJSONObject: self,
            options: []) {
            let theJSONText = String(data: theJSONData,
                                     encoding: .utf8)
            return theJSONText!
        }else{
            return ""
        }
    }
    public func json() -> String {
        if let theJSONData = try? JSONSerialization.data(
            withJSONObject: self,
            options: []) {
            let theJSONText = String(data: theJSONData,
                                     encoding: .utf8)
            return theJSONText!
        }else{
            return ""
        }
    }
}







