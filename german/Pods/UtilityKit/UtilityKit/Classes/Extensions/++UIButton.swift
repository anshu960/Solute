//
//  ++UIButton.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import UIKit
extension UIButton{
    public func setImageTint(color:UIColor){
        if let image = self.imageView?.image{
            self.imageView?.image = image.withRenderingMode(.alwaysTemplate)
            self.setImage(image, for: .normal)
            self.tintColor = color
        }
    }
}
