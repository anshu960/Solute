//  DesignableXibView.swift

import UIKit

@IBDesignable
class DesignableXibView: UIView {

    var contentView : UIView?


    @IBOutlet var verksc: UIButton!
    override init(frame: CGRect) {
        super.init(frame: frame)
        xibSetup()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        xibSetup()
    }

    func xibSetup() {
        contentView = loadViewFromNib()

        // use bounds not frame or it'll be offset
        contentView!.frame = bounds

        // Make the view stretch with containing view
        contentView!.autoresizingMask = [UIView.AutoresizingMask.flexibleWidth, UIView.AutoresizingMask.flexibleHeight]

        // Adding custom subview on top of our view (over any custom drawing > see note below)
        addSubview(contentView!)
    }

    func loadViewFromNib() -> UIView! {

        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: "DesignableXibView", bundle: bundle)
        let view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
        return view
    }

}
