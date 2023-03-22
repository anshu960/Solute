//
//  ++NSArray.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import Foundation

extension Array{
    public func string()->String{
        var result = ""
        for item in self{
            result.append("\(item),")
        }
        result = String(result.dropLast())
        return result
    }
}
extension NSArray{
    public func string()->String{
        var result = ""
        for item in self{
            result.append("\(item),")
        }
        result = String(result.dropLast())
        return result
    }
    public func intArray()->[Int]{
        var result :[Int] = []
        for item in self{
            let val = "\(item)"
            if let intVal = Int(val){
                result.append(intVal)
            }
        }
        return result
    }
    
    public func json(from object:Any) -> String? {
        guard let data = try? JSONSerialization.data(withJSONObject: self, options: []) else {
            return nil
        }
        return String(data: data, encoding: String.Encoding.utf8)
    }
}


extension Sequence where Iterator.Element == [String: Any] {
    public func json() -> String? {
        guard let data = try? JSONSerialization.data(withJSONObject: self, options: []) else {
            return nil
        }
        return String(data: data, encoding: String.Encoding.utf8)
    }
    
}
