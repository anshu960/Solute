//
//  CustomImageView.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import UIKit
@IBDesignable
public class CustomImageView: UIImageView {

    /*
     // Only override draw() if you perform custom drawing.
     // An empty implementation adversely affects performance during animation.
     override func draw(_ rect: CGRect) {
     // Drawing code
     }
     */
   public let dragPanGesture = UIPanGestureRecognizer()
   public let resizePinchGesture = UIPinchGestureRecognizer()

  @IBInspectable public var DragEnabled = false{
        didSet{
            if(self.DragEnabled){
                self.dragPanGesture.addTarget(self, action: #selector(dragPanGestureAction(_:)))
                self.addGestureRecognizer(self.dragPanGesture)
                self.isUserInteractionEnabled = true
            }else{
               self.removeGestureRecognizer(self.dragPanGesture)
            }
        }
    }

   @IBInspectable public var ResizeEnable = false {
        didSet{
            if(ResizeEnable){
                self.resizePinchGesture.addTarget(self, action: #selector(resizeView(_:)))
                self.addGestureRecognizer(self.resizePinchGesture)
                self.isUserInteractionEnabled = true
            }else{
                self.removeGestureRecognizer(self.resizePinchGesture)
            }
        }
    }

    @objc public func dragPanGestureAction(_ sender:UIPanGestureRecognizer) {
        if let window = self.superview{
            let oldFrame = self.frame
            let translation = sender.translation(in: window)
            sender.view!.center = CGPoint(x: sender.view!.center.x + translation.x, y: sender.view!.center.y + translation.y)
            sender.setTranslation(CGPoint.zero, in: window)
            self.validateEditedFrame(oldFrame: oldFrame)
        }
    }

    @objc func resizeView(_ sender:UIPinchGestureRecognizer) {

    }
  public  func validateEditedFrame(oldFrame:CGRect) {
        if let window = self.superview{
            var x = self.frame.minX
            var y = self.frame.minY
            let width = self.frame.width
            let height = self.frame.height
            if(self.frame.minX < 0){
                x = 0
            }
            if(self.frame.minY < 0){
                y = 0
            }
            if(self.frame.maxX > window.frame.width){
                x = window.bounds.width - self.frame.width
            }
            if(self.frame.maxY  > window.frame.height){
                y = window.bounds.height - self.frame.height
            }
            if(x+width > avoidDragFrame.origin.x && y+height > avoidDragFrame.origin.y){
//                x = avoidDragFrame.origin.x - self.frame.width
//                y = avoidDragFrame.origin.y - self.frame.height
                self.frame = oldFrame
            }else{
                self.frame = CGRect(x: x, y: y, width: self.frame.width, height: self.frame.height)
            }
        }

    }
   public var avoidDragFrame :CGRect = CGRect.zero

}
