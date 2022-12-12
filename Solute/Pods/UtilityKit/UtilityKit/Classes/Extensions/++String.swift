//
//  ExtendingClasses.swift
//  S-NOC
//
//  Created by Vivek Kumar on 12/5/17.
//  Copyright © 2017 Vivek Kumar. All rights reserved.
//

import UIKit

extension String{
    public func trim()->String{
        let trimmedString = self.trimmingCharacters(in: .whitespacesAndNewlines)
        return trimmedString
    }
}
//Extending string to get respnse data decoded
extension String {
    //: ### Base64 encoding a string
    public func base64Encoded() -> String? {
        if let data = self.data(using: .utf8) {
            return data.base64EncodedString()
        }
        return nil
    }
    //: ### Base64 decoding a string
    public func base64Decoded() -> String? {
        if let data = Data(base64Encoded: self, options: .ignoreUnknownCharacters) {
            if let value = String(data: data, encoding: .utf8){
                return value
            }else{
                return ""
            }
        }
        else{
            return ""
        }
    }
    //:###For Validation
    public func equalsIgnoreCase(_ string:String) -> Bool {
        if(self.lowercased() == string.lowercased()){
            return true
        }else{
            return false
        }
    }
    public func imageData()->Data?{
        if let data = self.data(using: String.Encoding.utf8){
            return Data(base64Encoded: data, options: .ignoreUnknownCharacters)
        }else{
            return nil
        }
    }
}

//Extension For Size
extension String{
    public func height(withConstrainedWidth width: CGFloat, font: UIFont) -> CGFloat {
        let constraintRect = CGSize(width: width, height: .greatestFiniteMagnitude)
        let boundingBox = self.boundingRect(with: constraintRect, options: .usesLineFragmentOrigin, attributes: [kCTFontAttributeName as NSAttributedString.Key: font], context: nil)
        
        return ceil(boundingBox.height)
    }
    public func width(with fontSize:CGFloat) -> CGFloat {
        let font  = UIFont.systemFont(ofSize: fontSize)
        let fontAttributes = [NSAttributedString.Key.font: font]
        
        let size = (self as NSString).size(withAttributes: fontAttributes)
        return size.width
    }
}

//Extending string to get response string converted to dictionart
extension String{
    //For adding key path
    public func append(keyPath:String)->String{
        return (self+"."+keyPath)
    }
    public func convertToDictionary() -> NSDictionary {
        let blankDict : NSDictionary = [:]
        if let data = self.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as! NSDictionary
            } catch {
                print(error.localizedDescription)
            }
        }
        return blankDict
    }
    public func mutableDictionary() -> NSMutableDictionary {
        let blankDict : NSMutableDictionary = [:]
        if let data = self.data(using: .utf8) {
            do {
                let dict = try JSONSerialization.jsonObject(with: data, options: []) as! NSDictionary
                return NSMutableDictionary(dictionary: dict)
            } catch {
                print(error.localizedDescription)
            }
        }
        return blankDict
    }
    public func toArray() -> NSArray {
        let blankDict : NSArray = ["Couldn't Convert"]
        if let data = self.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as! NSArray
            } catch {
                print(error.localizedDescription)
            }
        }
        return blankDict
    }
}

extension String{
    public func toImage(size:CGSize,font:UIFont,color:UIColor) -> UIImage {
        //        let textFontAttributes:[String : Any] = [
        //            NSFontAttributeName: font,
        //            NSForegroundColorAttributeName: color,
        //            ]
        //        UIGraphicsBeginImageContextWithOptions(size, false, 1)
        //        let rect = CGRect(x: 0, y: 0, width: size.width, height:size.height)
        //        self.draw(in: rect, withAttributes: textFontAttributes)
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        return newImage!
    }
    
   public func capitalizeFirstLetter() -> String {
        return prefix(1).uppercased() + self.lowercased().dropFirst()
    }
}


extension Data {
    public func hex(separator:String = "") -> String {
        return (self.map { String(format: "%02X", $0) }).joined(separator: separator)
    }
}


//MARK:Barcode
extension String{
    public func generateBarcode() -> UIImage? {
        //        let data = self.data(using: String.Encoding.ascii)
        
        //        if let filter = CIFilter(name: "CICode128BarcodeGenerator") {
        //            filter.setValue(data, forKey: "inputMessage")
        //            let transform = CGAffineTransform(scaleX: 3, y: 3)
        //
        //            if let output = filter.outputImage?.transformed(by: transform) {
        //                return UIImage(ciImage: output)
        //            }
        //        }
        return nil
    }
    public init(contentsOfFileName:String,ofType filetype :String){
        var result : String? = nil
        if let path = Bundle.main.path(forResource: contentsOfFileName, ofType: filetype) {
            do {
                let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
                if let json = String(data: data, encoding: .utf8){
                    result = json
                }else{
                }
            } catch {
                // handle error
            }
        }
        self = result!
    }
    
    
   public func json(named:String) -> String? {
        var resultString : String?
        if let path = Bundle.main.path(forResource: named, ofType: "json") {
            do {
                let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
                if let json = String(data: data, encoding: .utf8){
                    resultString = json
                }
            } catch {
                // handle error
            }
        }
        return resultString
    }
    
   public func dictionary() -> NSDictionary {
        let blankDict : NSDictionary = [:]
        if let data = self.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as! NSDictionary
            } catch {
                print(error.localizedDescription)
            }
        }
        return blankDict
    }
    
    public func toDictionary() -> [String:Any] {
        let blankDict : [String:Any] = [:]
        if let data = self.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as! [String:Any]
            } catch {
                print(error.localizedDescription)
                print("\n")
                print("Data is as \n" + self)
            }
        }
        return blankDict
    }
    
   public func dictionaryArray() -> [NSDictionary] {
        let blankDict : [NSDictionary] = [[:]]
        if let data = self.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as! [NSDictionary]
            } catch {
                print(error.localizedDescription)
                print("\n")
                print("Data is as \n" + self)
                return blankDict
            }
        }else{
            return blankDict
        }
    }
    
   public func array() -> NSArray {
        let blankArray : NSArray = []
        if let data = self.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as! NSArray
            } catch {
                print(error.localizedDescription)
            }
        }
        return blankArray
    }
    
   public func stringArray() -> [String] {
        var array : [String] = []
        let allStr = self.split(separator: ",")
        for item in allStr{
            array.append(String(item))
        }
        return array
    }
   public func intArray() -> [Int] {
        var array : [Int] = []
        let allStr = self.split(separator: ",")
        for item in allStr{
            if let val = Int(String(item)){
                array.append(val)
            }
        }
        return array
    }
}


//compied from friendly
extension String{
    
   
    
    
    
   
}

//MARK:UIImageextension

extension UIImageView {
    public func setImageColor(color: UIColor) {
        let templateImage = self.image?.withRenderingMode(UIImage.RenderingMode.alwaysTemplate)
        self.image = templateImage
        self.tintColor = color
    }
}









