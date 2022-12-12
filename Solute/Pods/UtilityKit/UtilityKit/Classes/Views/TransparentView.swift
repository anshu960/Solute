//
//  TransparentView.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 7/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import UIKit
@IBDesignable
public class TransparentView: UIView {

    public override init(frame: CGRect) {
        super.init(frame: frame)
    }

    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    public override func layoutSubviews() {
        super.layoutSubviews()
    }

    public override func didMoveToSuperview() {
        super.didMoveToSuperview()
        self.tapGesture = UITapGestureRecognizer()
        self.tapGesture.addTarget(self, action: #selector(onTap(_:)))
        self.addGestureRecognizer(self.tapGesture)
    }
    @IBInspectable var removeOnTap : Bool {
        get{
            return self.isRemoveOnTapEnbled
        }
        set{
            self.isRemoveOnTapEnbled = newValue
        }
    }
    private var isRemoveOnTapEnbled = true
    private var tapGesture = UITapGestureRecognizer()
    @objc private func onTap(_ sender:UITapGestureRecognizer){
        if(self.isRemoveOnTapEnbled){
            for v in self.subviews{
                v.removeFromSuperview()
            }
            self.removeFromSuperview()
        }
    }
}
