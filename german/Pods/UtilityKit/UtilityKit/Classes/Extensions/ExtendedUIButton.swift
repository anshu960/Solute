//
//  ExtendedUIButton.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import UIKit

public class ExtendedUIButton: UIButton {

    /*
     // Only override draw() if you perform custom drawing.
     // An empty implementation adversely affects performance during animation.
     override func draw(_ rect: CGRect) {
     // Drawing code
     }
     */
   public var dragGesture = UIPanGestureRecognizer()
   public var isDraggable = false{
        didSet{
            if(isDraggable){
                dragGesture = UIPanGestureRecognizer(target: self, action: #selector(self.draggedView(_:)))
                self.isUserInteractionEnabled = true
                self.addGestureRecognizer(dragGesture)
            }else{
                self.removeGestureRecognizer(dragGesture)
            }
        }
    }
    @objc public func draggedView(_ sender:UIPanGestureRecognizer){
        _ = sender.location(in: UIApplication.shared.keyWindow!.rootViewController!.view)
        let translation = sender.translation(in: self.superview)
        self.transform = CGAffineTransform(translationX: translation.x, y: translation.y)
        sender.setTranslation(CGPoint.zero, in: self.superview)
    }

}
