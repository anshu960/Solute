//
//  ++UIColor.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//
import UIKit
extension UIColor{
   public func toHex() -> String {
        var r:CGFloat = 0
        var g:CGFloat = 0
        var b:CGFloat = 0
        var a:CGFloat = 0
        self.getRed(&r, green: &g, blue: &b, alpha: &a)
        let rgb:Int = (Int)(r*255)<<16 | (Int)(g*255)<<8 | (Int)(b*255)<<0
        return String(format: "#%06x", rgb)
    }
    public func set(alpha to:CGFloat)->UIColor{
        var r:CGFloat = 0
        var g:CGFloat = 0
        var b:CGFloat = 0
        var a:CGFloat = 0
        self.getRed(&r, green: &g, blue: &b, alpha: &a)
        return UIColor(red: r/255, green: g/255, blue: b/255, alpha: to)
    }
    public func alpha(_ withAlpha:CGFloat)->UIColor{
        return self.set(alpha: withAlpha)
    }
}
