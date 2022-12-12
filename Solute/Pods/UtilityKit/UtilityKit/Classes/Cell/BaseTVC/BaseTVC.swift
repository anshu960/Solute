//
//  BaseTVC.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 29/08/19.
//

import UIKit

open class BaseTVC: UITableViewCell {

    public var onChange : ((Field)->Void)?
    public var field : Field!
    public var controller : UIViewController?
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

}
