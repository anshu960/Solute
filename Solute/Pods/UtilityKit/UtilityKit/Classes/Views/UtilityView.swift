//
//  UtilityView.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 16/09/18.
//


import UIKit
@IBDesignable
open class UtilityView: UIView {
    /*
     // Only override draw() if you perform custom drawing.
     // An empty implementation adversely affects performance during animation.
     override func draw(_ rect: CGRect) {
     // Drawing code
     }
     */
   public var animationDuration :Double = 0.25
   public var transparentView = UIView()
   public var removeOnTap = true
    public func present(on view:UIView,with animation:AnimationStyle) {
        switch animation {
        case .Pop:
            self.addAsPopup(on: view)
            break
        default:
            add(on: view)
            break
        }
    }
    public func present(on view:UIView,with animation:AnimationStyle,duration:Double) {
        self.animationDuration = duration
        switch animation {
        case .Pop:
            self.addAsPopup(on: view)
            break
        default:
            add(on: view)
            break
        }
    }
    public func shake() {
        let animation = CAKeyframeAnimation(keyPath: "transform.translation.x")
        animation.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.linear)
        animation.duration = 0.6
        animation.values = [-20.0, 20.0, -20.0, 20.0, -10.0, 10.0, -5.0, 5.0, 0.0 ]
        self.layer.add(animation, forKey: "shake")
    }
    
    private func add(on View:UIView) {
        View.addSubview(self)
    }
    func addAsPopup(on view:UIView) {
        if(self.removeOnTap){
            let tapGesture = UITapGestureRecognizer()
            tapGesture.addTarget(self, action: #selector(onTap(_:)))
            self.transparentView.addGestureRecognizer(tapGesture)
        }
        self.transparentView.backgroundColor = UIColor.black.alpha(0.5)
        self.transparentView.frame = view.bounds
        self.transparentView.alpha = 0
        self.alpha = 0
        view.addSubview(self.transparentView)
        view.addSubview(self)
        UIView.animate(withDuration: self.animationDuration) {
            self.transparentView.alpha = 1
            self.alpha = 1
        }
    }
    public func removeFromSuperview(animation:AnimationStyle,duration:Double) {
        self.animationDuration = duration
        switch animation {
        case .Pop:
            self.removeAsPopIn()
            break
        default:
            break
        }
    }
    private func removeAsPopIn() {
        UIView.animate(withDuration: self.animationDuration, animations: {
            self.transparentView.alpha = 0
            self.alpha = 0
        }) { (isCompleted) in
            if(isCompleted){
                self.transparentView.removeFromSuperview()
                self.removeFromSuperview()
            }
        }
    }
}
extension UtilityView{
    @objc func onTap(_ sender:UITapGestureRecognizer) {
        UIView.animate(withDuration: self.animationDuration,   animations: {
            self.transparentView.alpha = 0
            self.alpha = 0
        }) { (isCompleted) in
            if(isCompleted){
                self.transparentView.removeFromSuperview()
                self.removeFromSuperview()
            }
        }
    }
}


