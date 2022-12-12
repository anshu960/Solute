//
//  KeyPath.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//
import Foundation
public class KeyPath{
    
    var key : String = ""
    func append(_ newKey: String) -> KeyPath {
        self.key = key + "." + newKey
        return self
    }
    public var envelope: KeyPath {
        get{
            self.key = key + ".envelope"
            return self
        }
    }
    public var payload : KeyPath{
        get{
            self.key = key + ".payload"
            return self
        }
    }
    public var nodeId : KeyPath{
        get{
            self.key = key + ".NODE_ID"
            return self
        }
    }
    public var code : KeyPath{
        get{
            self.key = key + ".code"
            return self
        }
    }
    public var userBasicConf : KeyPath{
        get{
            self.key = key + ".userBasicConf"
            return self
        }
    }
    
    public var loginUserDetails : KeyPath{
        get{
            self.key = key + ".loginUserDetails"
            return self
        }
    }
    
}
